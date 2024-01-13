package com.cdq.taskprocessing.processorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.cdq.taskprocessing")
public class ProcessorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessorServiceApplication.class, args);
	}

}
