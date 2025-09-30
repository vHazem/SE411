package edu.psu.se411;

import java.io.FileReader;
import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args) {
        try {
            FileReader file = new FileReader("nonexistent.txt");
            System.out.println("File opened successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }
}
