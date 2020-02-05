package com.operationcredit.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
public class OperacionesCreditoMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OperacionesCreditoMsApplication.class, args);
	}

}
