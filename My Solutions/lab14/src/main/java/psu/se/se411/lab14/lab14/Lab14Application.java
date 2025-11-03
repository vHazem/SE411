package psu.se.se411.lab14.lab14;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
@EnableJdbcRepositories
public class Lab14Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab14Application.class, args);
	}

}
