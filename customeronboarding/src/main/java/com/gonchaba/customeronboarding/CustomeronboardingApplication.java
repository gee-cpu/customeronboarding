package com.gonchaba.customeronboarding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CustomeronboardingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomeronboardingApplication.class, args);
	}

}
