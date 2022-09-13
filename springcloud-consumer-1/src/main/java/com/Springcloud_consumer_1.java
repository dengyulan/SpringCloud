package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.Service")
public class Springcloud_consumer_1 {
    public static void main(String[] args) {
        SpringApplication.run(Springcloud_consumer_1.class, args);
    }
}
