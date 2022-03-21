package com.pb.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication @EnableCaching
public class Rest2Application {

	public static void main(String[] args) {
		SpringApplication.run(Rest2Application.class, args);
	}

}

/*
 * The main class of the REST API. Allows spring
 * boot to run the application and host it on a
 * localhost server.
 */
