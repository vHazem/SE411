package psu.se.se411.lab14.lab14.model;

import org.springframework.data.annotation.Id;

public record Product (
		@Id Integer id,
		String name,
		String description,
		Double price
) {}
