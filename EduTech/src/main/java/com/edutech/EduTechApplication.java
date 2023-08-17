package com.edutech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.edutech")
public class EduTechApplication {

	public static void main(String[] args) {
		SpringApplication.run(EduTechApplication.class, args);
	}

}
