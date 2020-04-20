package com.basic.integrate;

import java.util.concurrent.TimeUnit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

import okhttp3.OkHttpClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableHystrix
@EnableTransactionManagement
@MapperScan("com.basic.integrate.dao")
public class BasicIntegrateApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicIntegrateApplication.class, args);
	}
	
	@Bean
	public OkHttpClient okHttpClient() {
		OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
												.connectTimeout(5, TimeUnit.SECONDS)
												.readTimeout(5, TimeUnit.SECONDS)
												.build();
		return okHttpClient;
	}
}
