package edu.psu.se411.lab09_salaries.model;

import java.util.Date;

import edu.psu.se411.lab09_salaries.exceptions.MissingInformationException;

public abstract class Employee extends Person {

	private Date dateOfEmployment;
	
	public Employee(String id_p, String fname_p, String lname_p, Date dob_p, Date dateOfEmployment_p) {
		super(id_p, fname_p, lname_p, dob_p);
		this.dateOfEmployment = dateOfEmployment_p;
	}

	public Date getDateOfEmployment() {
		return dateOfEmployment;
	}
	
	public abstract Double getSalary() throws MissingInformationException;

}
