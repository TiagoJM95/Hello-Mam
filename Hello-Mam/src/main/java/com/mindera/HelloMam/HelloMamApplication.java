package com.mindera.HelloMam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@EnableCaching
public class HelloMamApplication {
	public static void main(String[] args) {
		SpringApplication.run(HelloMamApplication.class, args);
	}

	/*hello-mam:
	build: ./Hello-Mam
	container_name: hello-mam
	ports:
			- "8080:8080"

	hello-grandma:
	build: ./hello-grandma
	container_name: hello-grandma
	ports:
			- "8082:8080"*/

}