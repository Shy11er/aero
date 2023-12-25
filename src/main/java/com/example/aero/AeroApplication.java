package com.example.aero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableScheduling
public class AeroApplication {
	public static void main(String[] args) {
		SpringApplication.run(AeroApplication.class, args);
	}


	@Configuration
	public class cors implements WebMvcConfigurer {
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**")
					.allowedOrigins("http://localhost:5173")
					.allowedMethods("GET", "POST", "PUT", "DELETE")
					.allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept")
					.allowCredentials(true)
					.maxAge(3600);
		}
	}
}
