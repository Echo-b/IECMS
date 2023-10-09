package cn.edu.cnu.iiot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
public class IiotApplication {

    public static void main(String[] args) {
        SpringApplication.run(IiotApplication.class, args);
    }

}
