package edu.psu.se411.lab09_salaries.model;

import java.util.Date;

public abstract class Person {
	
	private String id;
	private String fname;
	private String lname;
	
	/**	Date of birth */
	private Date dob;
	
	public String getId() {
		return id;
	}
	
	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Person(String id_p, String fname_p, String lname_p, Date dob_p) {
		this.id = id_p;
		this.fname = fname_p;
		this.lname = lname_p;
		this.dob = dob_p;
	}
	
}
