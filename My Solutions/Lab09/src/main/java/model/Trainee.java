package model;

import java.time.LocalDate;

import config.Config;
import exceptions.InvalidArgumentException;
import exceptions.MissingInformationException;

public class Trainee extends Employee {
    private Double score; // Nullable, given monthly by manager

    public Trainee(String id, String firstName, String lastName, LocalDate dob, String email,
                   LocalDate employmentDate) {
        super(id, firstName, lastName, dob, email, employmentDate);
        this.score = null;
    }

    public void setScore(double score) throws InvalidArgumentException {
        if (score < Config.MIN_SCORE || score > Config.MAX_SCORE) {
            throw new InvalidArgumentException("Score must be between 0 and 1");
        }
        this.score = score;
    }

    @Override
    public double calculateSalary() throws MissingInformationException, InvalidArgumentException {
        if (score == null) {
            throw new MissingInformationException("Score not specified for trainee");
        }
        if (score < Config.MIN_SCORE || score > Config.MAX_SCORE) {
            throw new InvalidArgumentException("Score must be between 0 and 1");
        }
        return score * Config.TRAINEE_FIXED_RATE;
    }

    public Double getScore() {
        return score;
    }
}

