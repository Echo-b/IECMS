package cn.edu.cnu.iiot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "mqtt")
public class MQTTConfig {
    private  String address;
    private String client_id;
    private String user_name;
    private String password;
    private String subTopic;
    private String pubTopic;
    private int keep_alive;
    private int timeout;

}
