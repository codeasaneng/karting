package com.example.karting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.karting.model")
public class KartingApplication {

	public static void main(String[] args) {
		SpringApplication.run(KartingApplication.class, args);
	}

}
