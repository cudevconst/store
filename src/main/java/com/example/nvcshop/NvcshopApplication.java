package com.example.nvcshop;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NvcshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(NvcshopApplication.class, args);
	}

}
