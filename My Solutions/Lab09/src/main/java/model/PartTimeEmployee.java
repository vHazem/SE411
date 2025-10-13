package model;

import java.time.LocalDate;

import config.Config;
import exceptions.InvalidArgumentException;
import exceptions.MissingInformationException;

public class PartTimeEmployee extends Employee {
    private double hourlyRate;
    private Integer hoursWorked; // Use Integer to allow null (not specified)

    public PartTimeEmployee(String id, String firstName, String lastName, LocalDate dob, String email,
                            LocalDate employmentDate, double hourlyRate) {
        super(id, firstName, lastName, dob, email, employmentDate);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = null;
    }

    public void setHoursWorked(int hoursWorked) throws InvalidArgumentException {
        if (hoursWorked < 0 || hoursWorked > Config.MAX_HOURS) {
            throw new InvalidArgumentException("Hours worked must be between 0 and " + Config.MAX_HOURS);
        }
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateSalary() throws MissingInformationException, InvalidArgumentException {
        if (hoursWorked == null) {
            throw new MissingInformationException("Hours worked not specified for part-time employee");
        }
        if (hoursWorked < 0 || hoursWorked > Config.MAX_HOURS) {
            throw new InvalidArgumentException("Hours worked must be between 0 and " + Config.MAX_HOURS);
        }
        return hourlyRate * hoursWorked;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public Integer getHoursWorked() {
        return hoursWorked;
    }
}
