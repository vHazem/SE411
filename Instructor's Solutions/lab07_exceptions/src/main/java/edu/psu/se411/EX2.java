package edu.psu.se411;

import edu.psu.se411.exceptions.InvalidAgeException;

public class EX2 {

	static void validateAge(int age) throws InvalidAgeException {
		if (age < 18) {
			throw new InvalidAgeException("Age must be 18 or older.");
		} else {
			System.out.println("Valid age.");
		}
	}

	public static void main(String[] args) {
		try {
			validateAge(15);
		} catch (InvalidAgeException e) {
			System.out.println("Exception caught: " + e.getMessage());
		}
	}
	
}
