package com.example.gestiondesformations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@Configuration
public class GestionDesFormationsApplication implements WebMvcConfigurer {
	public static void main(String[] args) {
		SpringApplication.run(GestionDesFormationsApplication.class, args);
	}
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("http://localhost:4200") // Allow requests from any origin
				.allowedMethods("GET", "POST", "PUT", "DELETE") // Allowed HTTP methods
				.allowedHeaders("*"); // Allowed headers
	}

}
