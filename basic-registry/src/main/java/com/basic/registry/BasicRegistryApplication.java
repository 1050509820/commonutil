package com.basic.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@EnableEurekaServer
@SpringBootApplication
public class BasicRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicRegistryApplication.class, args);
	}

}
