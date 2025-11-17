package psu.edu.ch06.crud04.model;

import org.springframework.data.annotation.Id;

public record User(
		@Id Integer id,
		String username,
		String password
) {
}
