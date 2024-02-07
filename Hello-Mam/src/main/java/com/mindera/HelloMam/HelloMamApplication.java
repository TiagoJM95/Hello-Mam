package com.mindera.HelloMam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HelloMamApplication {
	public static void main(String[] args) {
		SpringApplication.run(HelloMamApplication.class, args);
	}
}