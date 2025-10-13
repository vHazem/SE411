package edu.psu.se411.lab09_salaries.model;

import java.util.Date;

import edu.psu.se411.lab09_salaries.exceptions.InvalidArgumentException;

public class FullTimeEmployee extends Employee {
	
	private Double monthlySalary;

	public FullTimeEmployee(String id_p, String fname_p, String lname_p, Date dob_p, 
			Date dateOfEmployment_p, Double monthlySalary_p) throws InvalidArgumentException {
		
		super(id_p, fname_p, lname_p, dob_p, dateOfEmployment_p);
		
		if(monthlySalary_p == null || monthlySalary_p < 0)
			throw new InvalidArgumentException("The monthly salary cannot be negative nor null");
		
		this.monthlySalary = monthlySalary_p;
	}

	@Override
	public Double getSalary() {
		return this.monthlySalary;
	}

}
