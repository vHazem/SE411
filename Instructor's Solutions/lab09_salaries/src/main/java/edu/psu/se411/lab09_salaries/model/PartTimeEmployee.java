package edu.psu.se411.lab09_salaries.model;

import java.util.Date;

import edu.psu.se411.lab09_salaries.AppConfiguration;
import edu.psu.se411.lab09_salaries.exceptions.InvalidArgumentException;
import edu.psu.se411.lab09_salaries.exceptions.MissingInformationException;

public class PartTimeEmployee extends Employee {

	private Double hourlyRate;
	private Integer hoursWorked;

	public PartTimeEmployee(String id_p, String fname_p, String lname_p, Date dob_p, Date dateOfEmployment_p,
			Double hourlyRate_p) throws InvalidArgumentException {
		
		super(id_p, fname_p, lname_p, dob_p, dateOfEmployment_p);
		
		if(hourlyRate_p < 0)
			throw new InvalidArgumentException("The hourly rate cannot be negative");
		
		this.hourlyRate = hourlyRate_p;
	}

	@Override
	public Double getSalary() throws MissingInformationException {
		if(hoursWorked == null)
			throw new MissingInformationException("The number of hours worked is not defined");
		return this.hourlyRate * this.hoursWorked;
	}
	
	public void setHoursWorked(Integer hoursWorked_p) throws IllegalArgumentException, InvalidArgumentException {
		
		if(hoursWorked_p > AppConfiguration.MAX_WORKED_HOURS_PER_MONTH)
			throw new InvalidArgumentException("The total hours worked per month cannot exceed " +
					AppConfiguration.MAX_WORKED_HOURS_PER_MONTH);
		
		if(hoursWorked_p < 0)
			throw new InvalidArgumentException("The total hours worked per month cannot be negative");
		
		this.hoursWorked = hoursWorked_p;
	}

}
