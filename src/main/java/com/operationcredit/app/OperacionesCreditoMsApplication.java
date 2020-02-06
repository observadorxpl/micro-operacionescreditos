package com.operationcredit.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.operationcredit.app.repository.ITypeOperationRepo;
@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
public class OperacionesCreditoMsApplication implements CommandLineRunner{
	@Autowired
	private ITypeOperationRepo typeRepo;
	public static void main(String[] args) {
		SpringApplication.run(OperacionesCreditoMsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

}
