package com.clientui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.clientui")
@EnableEurekaClient
public class Clientui2Application {

    public static void main(String[] args) {
        SpringApplication.run(Clientui2Application.class, args);
    }

}
