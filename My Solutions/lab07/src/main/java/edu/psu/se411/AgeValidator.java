package edu.psu.se411;

import edu.psu.se411.exception.InvalidAgeException;

public class AgeValidator {
    public static void validateAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age is below 18. Access Denied.");
        }
        System.out.println("Age is valid. Access Granted.");
    }

    public static void main(String[] args) {
        try {
            validateAge(20); 
        } catch (InvalidAgeException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
