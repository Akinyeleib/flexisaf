package com.flexisaf.tasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class FlexisafApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlexisafApplication.class, args);
	}

	@GetMapping()
	public String welcome() {
		return "Welcome to Flexisaf";
	}

}
