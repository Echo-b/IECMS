package cn.edu.swjtu.service.impl;

import cn.edu.swjtu.config.MQTTConfig;
import cn.edu.swjtu.mapper.DeviceMapper;
import cn.edu.swjtu.pojo.ControllInfo;
import cn.edu.swjtu.result.ResponseData;
import cn.edu.swjtu.service.MQTTService;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 此类为mqtt各种操作的实现
 * @author Curitis
 */
@Service
public class MQTTServiceImpl implements MQTTService, MqttCallbackExtended {
    @Autowired
    private MQTTConfig mqttConfig;

    @Autowired
    private DeviceMapper mapper;

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
    public ResponseData pubMqttMsg(ControllInfo c) {
        try {
            if(!this.client.isConnected()){
                this.connectMQTT();
                if(!this.client.isConnected()){
                    System.out.println("mqtt connect fault");
                }
            }
            else {
                String encodeCommand = "";
                // 此处command可能需要进一步解析
                switch (c.getCommand()){
                    case "LEDON" : encodeCommand = "{\"LED\":1}"; break;
                    case "LEDOFF" : encodeCommand = "{\"LED\":0}"; break;
                }
                MqttMessage message = new MqttMessage();
                message.setPayload(encodeCommand.getBytes());
                System.out.println("message = " + message);
                this.client.publish(c.getTopic(),message);
                System.out.println("消息推送至mqtt server成功");
                // 推送至mqtt的消息可以重新封装成标准的json格式，这样便于嵌入式部分解析
                if(mapper.DeviceControll(c) == 1){
                    System.out.println("消息记录已存储至数据库");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseData.success("success");
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
        System.out.println("sender = " + sender);
        /**
         * 下述到达消息为嵌入式设备采集并发送至mqtt服务器的温湿度，需将温湿度进行处理存入normal_data数据表
         * 如果温湿度过高，那么存入alter_info数据表，基准值自由设置
         * 此外为了防止数据存入过多，应采集一段数据然后求平均值在进行存入，或者设备端控制发送数据的时间
         */
        String str = new String(mqttMessage.getPayload());
        System.out.println("str = " + str);

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
