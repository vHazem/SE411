package edu.psu.se411.lab09_salaries.exceptions;

public class InvalidArgumentException extends Exception {

	public InvalidArgumentException(String message) {
		super("Invalid argument exception:" + message);
	}
	
}
