package com.aquaindica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AquaindicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AquaindicaApplication.class, args);
	}

}
