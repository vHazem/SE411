package model;

import java.time.LocalDate;

public class FullTimeEmployee extends Employee {
    private double fixedSalary;

    public FullTimeEmployee(String id, String firstName, String lastName, LocalDate dob, String email,
                            LocalDate employmentDate, double fixedSalary) {
        super(id, firstName, lastName, dob, email, employmentDate);
        this.fixedSalary = fixedSalary;
    }

    @Override
    public double calculateSalary() {
        return fixedSalary;
    }

    public double getFixedSalary() {
        return fixedSalary;
    }
}
