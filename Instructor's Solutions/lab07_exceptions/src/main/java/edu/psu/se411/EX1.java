package edu.psu.se411;

import java.io.FileReader;
import java.io.IOException;

public class EX1 {

	public static void main(String[] args) {
		// Checked Exception Example
		try {
			FileReader file = new FileReader("nonexistent.txt");
		} catch (IOException e) {
			System.out.println("Checked Exception: " + e.getMessage());
		}

		// Unchecked Exception Example
		String text = null;
		try {
			System.out.println(text.length());
		} catch (NullPointerException e) {
			System.out.println("Unchecked Exception: " + e.getMessage());
		}
	}

}
