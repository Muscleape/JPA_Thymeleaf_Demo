package com.example.JPA_Thymeleaf_Demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class JpaThymeleafDemoApplication extends SpringBootServletInitializer {

	@Override
	public SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(JpaThymeleafDemoApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(JpaThymeleafDemoApplication.class, args);
	}
}
