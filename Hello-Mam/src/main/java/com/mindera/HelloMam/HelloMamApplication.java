package com.mindera.HelloMam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableCaching
@RestController
public class HelloMamApplication {

	@GetMapping("/message")
	public String getMessage(){
		return "Welcome to my app";
	}
	public static void main(String[] args) {
		SpringApplication.run(HelloMamApplication.class, args);
	}

}