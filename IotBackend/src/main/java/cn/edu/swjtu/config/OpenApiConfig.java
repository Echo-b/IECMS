package cn.edu.swjtu.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI().info(new Info() //
                .title("IECMS Lite API") //
                .description("智能设备控制与管理系统API设计文档") //
                .version("0.0.1"));
    }

}