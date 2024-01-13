package com.cdq.taskprocessing.intakeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.cdq.taskprocessing")
public class IntakeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntakeServiceApplication.class, args);
	}

}
