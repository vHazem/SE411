package edu.psu.se411.lab09_salaries.exceptions;

public class MissingInformationException extends Exception {

	public MissingInformationException(String message) {
		super("Missing information:" + message);
	}
	
}
