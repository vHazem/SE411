package edu.psu.se411.lab09_salaries.model;

import java.util.Date;

import edu.psu.se411.lab09_salaries.utils.ModelUtils.ContactMethod;

public class Client extends Person {

	private String phoneNumber;
	private ContactMethod preferredContactMethod;

	public Client(String id_p, String fname_p, String lname_p, Date dob_p, String phoneNumber_p,
			ContactMethod preferredContactMethod_p) {
		super(id_p, fname_p, lname_p, dob_p);
		this.phoneNumber = phoneNumber_p;
		this.preferredContactMethod = preferredContactMethod_p;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public ContactMethod getPreferredContactMethod() {
		return preferredContactMethod;
	}

	public void setPreferredContactMethod(ContactMethod preferredContactMethod) {
		this.preferredContactMethod = preferredContactMethod;
	}

}
