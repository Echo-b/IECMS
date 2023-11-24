package cn.edu.swjtu.service;

import cn.edu.swjtu.pojo.CommandInfo;
import cn.edu.swjtu.result.ResponseData;

public interface MQTTService {
    /**
     * 订阅消息
     * TODO 增加多级订阅
     */
    public void subMqttMsg();

    /**
     * 向对应的topic发送消息
     * @param topic
     * @param msg
     */
    public ResponseData pubMqttMsg(CommandInfo c);

    /**
     * 连接mqtt
     * @return Boolean 是否连接成功
     */
    public  boolean connectMQTT();

    /**
     * 初始化mqtt连接服务各项参数
     * @return 是否初始化成功
     */
    public  boolean init();

}
