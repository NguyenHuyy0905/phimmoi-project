package com.phimmoi.techwizapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping
public class TechwizapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechwizapiApplication.class, args);
	}

	@GetMapping("/")
	public String test() {
		return "Hello World";
	}

}
