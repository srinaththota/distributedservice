package com.assignment.messageservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.assignment.messageservice"})
public class MessageserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageserviceApplication.class, args);
	}

}
