package cn.edu.swjtu.service.impl;

import cn.edu.swjtu.config.JsonConfig;
import cn.edu.swjtu.config.MQTTConfig;
import cn.edu.swjtu.mapper.DeviceMapper;
import cn.edu.swjtu.mapper.RecordMapper;
import cn.edu.swjtu.pojo.CommandInfo;
import cn.edu.swjtu.pojo.TData;
import cn.edu.swjtu.pojo.Threshold;
import cn.edu.swjtu.result.ResponseData;
import cn.edu.swjtu.service.MQTTService;
import cn.edu.swjtu.utils.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

import static cn.edu.swjtu.utils.DateUtil.getDate;


/**
 * 此类为mqtt各种操作的实现
 * @author Curitis
 */
@Service
public class MQTTServiceImpl implements MQTTService, MqttCallbackExtended {
    @Autowired
    private MQTTConfig mqttConfig;

    @Autowired
    private RecordMapper mapper;

    @Autowired
    private RedisTemplate redisTemplate;

    private MqttClient client;
    private MqttConnectOptions options;

    @Override
    public  boolean connectMQTT(){
        try{
            IMqttToken token = client.connectWithResult(options);
            if(token.isComplete()){
                System.out.println("MQTT Connect Success!!!");
            }
            else{
                System.out.println("MQTT Connect Failed!!!");
            }
            return token.isComplete();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("error in ConnectMQTT");
        }
        return false;
    }
    @Autowired
    @Override
    public boolean init() {
        if(client == null){
            try {
                //mqttConfig = new MQTTConfig();
                options = new MqttConnectOptions();
//                mqttCallBack = new MQTTCallBack();
                client = new MqttClient(mqttConfig.getAddress(),MqttClient.generateClientId());
                options.setUserName(mqttConfig.getUser_name());
                options.setPassword(mqttConfig.getPassword().toCharArray());
                options.setConnectionTimeout(mqttConfig.getTimeout());
                options.setKeepAliveInterval(mqttConfig.getKeep_alive());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public void subMqttMsg() {
        try {
            if(!client.isConnected()){
                this.connectMQTT();
            }
            System.out.println("set mqttCallBack");
            client.setCallback(this);
            System.out.println("finish setCallback");
            client.subscribe(mqttConfig.getSubTopic()+"/#");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ResponseData pubMqttMsg(CommandInfo c) {
        try {
            if(!this.client.isConnected()){
                this.connectMQTT();
                if(!this.client.isConnected()){
                    System.out.println("mqtt connect fault");
                }
            }
            else {
                /**
                 * Command控制指令格式
                 * {
                 *     "command":{
                 *         "led":{
                 *             "status":"on"
                 *         },
                 *         "htSensor":{
                 *             "temperature":23,
                 *             "humidity":28,
                 *             "status":"on"
                 *         }
                 *     }
                 * }
                 */
                // todo: if the command contain topic then we should parse and publish it to the mqtt server
                if(!c.getTopic().equals("")) {
                    JSONObject root = new JSONObject();
                    JSONObject command = new JSONObject();
                    JSONObject led = new JSONObject();
                    JSONObject beep = new JSONObject();
                    JSONObject delay = new JSONObject();
                    JSONObject htSensor = new JSONObject();
                    // todo: add more command type
                    switch (c.getCommand()){
                        case "LEDON" : {
                            led.put("status","on");
                            led.put("delay",0);
                            break;
                        }
                        case "LEDOFF" : {
                            led.put("status","off");
                            led.put("delay",0);
                            break;
                        }
                        case "BEEPON" : {
                            beep.put("status","on");
                            beep.put("delay",0);
                            break;
                        }
                        case "BEEPOFF" : {
                            beep.put("status","off");
                            beep.put("delay",0);
                            break;
                        }
                        case "BEEPALERT" : {
                            beep.put("status","on");
                            beep.put("delay",500);
                            break;
                        }
                        case "DELAYOPEN" : {
                            delay.put("status","on");
                            delay.put("did",c.getDid());
                            break;
                        }
                        case "DELAYCLOSE" : {
                            delay.put("status","off");
                            delay.put("did",c.getDid());
                            break;
                        }
                    }

                    // 推送至mqtt的消息重新封装成标准的json格式，这样便于嵌入式部分解析
                    command.put("led",led);
                    command.put("beep",beep);
                    command.put("htSensor",htSensor);
                    command.put("delay",delay);
                    root.put("command",command);

                    System.out.println("root = " + root);

                    MqttMessage message = new MqttMessage();
                    message.setPayload(root.toJSONString().getBytes());
                    System.out.println("message = " + message);
                    this.client.publish(c.getTopic(),message);
                    System.out.println("消息推送至mqtt server成功");
                }

                c.setDate(getDate());
                if(mapper.InsertCommandRecord(c) == 1){
                    System.out.println("消息记录已存储至数据库");
                    return ResponseData.success("success");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseData.error("failure");
    }

    @Override
    public void connectComplete(boolean b, String s) {
        System.out.println("reconnect success!!");
        System.out.println("begin re-subsript");
        this.subMqttMsg();
    }

    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("mqtt connect lost....");
        try {
            while(!client.isConnected()){
                System.out.println("try connect mqtt");
                this.connectMQTT();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        String sender = s.substring(s.indexOf("/") + 1);
//        System.out.println("sender = " + sender);
        /**
         * 下述到达消息为嵌入式设备采集并发送至mqtt服务器的温湿度，需将温湿度进行处理存入normal_data数据表
         * 如果温湿度过高，那么存入alter_info数据表，基准值自由设置
         * 此外为了防止数据存入过多，应采集一段数据然后求平均值在进行存入，或者设备端控制发送数据的时间
         */
        String str = new String(mqttMessage.getPayload());
        JSONObject data = JSON.parseObject(str);
        JSONObject lightSensor = (JSONObject) data.get("lightSensor");
        JSONObject htSensor = (JSONObject) data.get("htSensor");
        JSONObject led = (JSONObject) data.get("Led");
        Double temperature = ((BigDecimal)htSensor.get("Temp")).doubleValue();
        Double humidity = ((BigDecimal)htSensor.get("Humi")).doubleValue();
        int htSensorDid = htSensor.getIntValue("did");
        Double light = ((BigDecimal) lightSensor.get("Light")).doubleValue();
        int lightDid = lightSensor.getIntValue("did");
        int ledStatus = led.getIntValue("status");
        int ledDid = led.getIntValue("did");

        Threshold htSensorThreshold = (Threshold) redisTemplate.opsForValue().get(htSensorDid);
        Threshold lightSensorThreshold = (Threshold) redisTemplate.opsForValue().get(lightDid);

//        System.out.println("light = " + light);
        if(light > lightSensorThreshold.getLight_max() || temperature > htSensorThreshold.getTemp_max() || humidity > htSensorThreshold.getHumi_max()) {
            CommandInfo c = new CommandInfo();
            c.setDid(lightDid);
            c.setCommand("BEEPALERT");
            c.setTopic("/topics/sub/beep");
            c.setDeviceName("beep");
            c.setDate(DateUtil.getDate());
            pubMqttMsg(c);
        }
//        System.out.println("str = " + str);

    }

    public static void handle(String sender, String str) {
        try {
            System.out.println("sender = " + sender);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}