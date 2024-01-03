package com.example.ssdassda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SsdassdaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsdassdaApplication.class, args);
	}

}
