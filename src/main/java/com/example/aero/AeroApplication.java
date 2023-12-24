package com.example.aero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AeroApplication {
	public static void main(String[] args) {
		SpringApplication.run(AeroApplication.class, args);
	}

}
