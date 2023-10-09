package cn.edu.cnu.iiot.service.impl;

import cn.edu.cnu.iiot.config.MQTTConfig;
import cn.edu.cnu.iiot.mapper.DataMapper;
import cn.edu.cnu.iiot.pojo.AlertInfo;
import cn.edu.cnu.iiot.pojo.NormalData;
import cn.edu.cnu.iiot.pojo.TData;
import cn.edu.cnu.iiot.service.MqttActions;
import cn.edu.cnu.iiot.utils.NewtonInterPolation;
import com.alibaba.fastjson.JSON;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


/**
 * 此类为mqtt各种操作的实现
 * @author Curitis
 */
@Service
public class MqttActionsImpl implements MqttActions, MqttCallbackExtended {
    @Autowired
    private MQTTConfig mqttConfig;
    private MqttClient client;
    private MqttConnectOptions options;

    @Autowired
    private DataMapper mapper;

    private ArrayList<Float> remainValues = new ArrayList<>();

//    @Autowired
//    DeviceService deviceServer;
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
    public void pubMqttMsg(String topic,String msg) {
        try {
            if(!this.client.isConnected()){
                this.connectMQTT();
                if(!this.client.isConnected()){
                    System.out.println("mqtt connect fault");
                }
            }
            else {
                MqttMessage message = new MqttMessage();
                message.setPayload(msg.getBytes());
                this.client.publish(mqttConfig.getPubTopic()+"/"+topic,message);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
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
        String str = new String(mqttMessage.getPayload());
        handle(sender, str, remainValues, mapper);
    }

    public static void handle(String sender, String str, ArrayList<Float> remainValues, DataMapper mapper) {
        try {
            TData t = JSON.parseObject(str,TData.class);
            System.out.println("t = " + t);
            float temperature = t.getTemperature();
            System.out.println("temperature = " + temperature);
            String date = t.getDate();

            if(temperature > -99){
                remainValues.add(temperature);
            } else {
                if(remainValues.size() >= 4){
                    float[] xList = new float[3];
                    float[] yList = new float[3];

                    for (int i = 0; i < 3; i++) {
                        xList[i] = remainValues.get(i);
                        yList[i] = remainValues.get(i+1);
                    }

                    NewtonInterPolation newton = new NewtonInterPolation();
                    float predictValue = newton.BELIE(xList,yList, remainValues.get(3));
                    for (Float aFloat : xList) {
                        System.out.println("xFloat = " + aFloat);
                    }
                    for (Float aFloat : yList) {
                        System.out.println("yFloat = " + aFloat);
                    }
                    System.out.println("predictValue = " + predictValue);

                    t.setTemperature(predictValue);
                }

            }
            while(remainValues.size() > 4){
                remainValues.remove(0);
            }
            if(t.getTemperature() > 30.0){
                AlertInfo info  = new AlertInfo(0,t.getDid(),t.getTemperature(),sender,date);
                if(mapper.insertAlertInfo(info) != 0){
                    System.out.println("insert alert info successful");
                }
            } else{
                NormalData normalData = new NormalData(0,t.getDid(), t.getTemperature(), date);
                if(mapper.insertNoramlData(normalData) != 0){
                    System.out.println("insert normal data successful" );
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
