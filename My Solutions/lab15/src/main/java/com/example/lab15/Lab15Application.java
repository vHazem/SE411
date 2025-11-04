package com.example.lab15;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
@EnableJdbcRepositories
public class Lab15Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab15Application.class, args);
	}

}
