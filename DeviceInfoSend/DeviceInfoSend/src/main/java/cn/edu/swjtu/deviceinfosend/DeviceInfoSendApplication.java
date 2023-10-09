package cn.edu.swjtu.deviceinfosend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("cn.edu.swjtu.deviceinfosend.mapper")
public class DeviceInfoSendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeviceInfoSendApplication.class, args);
    }

}
