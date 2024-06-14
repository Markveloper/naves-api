package com.prueba.naves_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class NaveApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NaveApiApplication.class, args);
	}

}
