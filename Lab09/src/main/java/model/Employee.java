package model;
import java.time.LocalDate;

import exceptions.InvalidArgumentException;
import exceptions.MissingInformationException;

public abstract class Employee extends Person {
	private LocalDate employmentDate;

	public Employee(String id, String firstName, String lastName, LocalDate dob, String email,
			LocalDate employmentDate) {
		super(id, firstName, lastName, dob, email);
		this.employmentDate = employmentDate;
	}

	public LocalDate getEmploymentDate() {
		return employmentDate;
	}

	// Polymorphic salary calculation method
	public abstract double calculateSalary() throws MissingInformationException, InvalidArgumentException;
}
