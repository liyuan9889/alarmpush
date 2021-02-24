package com.tuhui.alarmpush;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.tuhui.alarmpush.mapper")
public class AlarmpushApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlarmpushApplication.class, args);
    }

}
