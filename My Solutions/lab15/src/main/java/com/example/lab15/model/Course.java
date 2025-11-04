package com.example.lab15.model;

import org.springframework.data.annotation.Id;

public record Course(
		@Id Integer id,
		String code,
		String name,
		String description,
		Integer credits
) {}
