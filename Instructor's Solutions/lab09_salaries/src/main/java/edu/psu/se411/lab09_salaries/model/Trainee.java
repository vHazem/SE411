package edu.psu.se411.lab09_salaries.model;

import java.util.Date;

import edu.psu.se411.lab09_salaries.AppConfiguration;
import edu.psu.se411.lab09_salaries.exceptions.InvalidArgumentException;
import edu.psu.se411.lab09_salaries.exceptions.MissingInformationException;

public class Trainee extends Employee {

	private Double score;
	
	public Trainee(String id_p, String fname_p, String lname_p, Date dob_p, Date dateOfEmployment_p) {
		super(id_p, fname_p, lname_p, dob_p, dateOfEmployment_p);
	}

	public void setScore(Double score_p) throws InvalidArgumentException {
		if(score_p > 1 || score_p < 0)
			throw new InvalidArgumentException("The score of the trainee has to be between 0 and 1");
		
		this.score = score_p;
	}
	
	@Override
	public Double getSalary() throws MissingInformationException {
		if(score == null)
			throw new MissingInformationException("The score of the trainee is missing");
		
		return score * AppConfiguration.FIXED_MOTHLY_RATE_TRAINEES;
	}
	

}
