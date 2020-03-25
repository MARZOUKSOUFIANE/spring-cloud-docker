package com.mcommandes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients("com.mcommandes")
public class McommandesApplication {

	public static void main(String[] args) {
		SpringApplication.run(McommandesApplication.class, args);
	}
}
