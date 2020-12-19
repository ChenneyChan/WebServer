package com.chenney.webProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.chenney.webProject.controller")
public class WebProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebProjectApplication.class, args);
	}

}
