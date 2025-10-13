package app;

import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.*;

import exceptions.InvalidArgumentException;
import exceptions.MissingInformationException;
import model.FullTimeEmployee;
import model.PartTimeEmployee;
import model.Trainee;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            setupLogger();

            logger.info("System start");

            // Create employees
            FullTimeEmployee fte = new FullTimeEmployee("E001", "Alice", "Smith",
                    LocalDate.of(1985, 5, 20), "alice@example.com",
                    LocalDate.of(2010, 1, 1), 4000.0);

            PartTimeEmployee pte = new PartTimeEmployee("E002", "Bob", "Johnson",
                    LocalDate.of(1990, 8, 15), "bob@example.com",
                    LocalDate.of(2020, 3, 10), 20.0);

            Trainee trainee = new Trainee("E003", "Charlie", "Brown",
                    LocalDate.of(2000, 7, 1), "charlie@example.com",
                    LocalDate.of(2023, 6, 1));

            // Set part-time hours and trainee score with valid values
            pte.setHoursWorked(160);
            trainee.setScore(0.85);

            // Calculate salaries
            System.out.println("Full Time Employee Salary: " + fte.calculateSalary());
            System.out.println("Part Time Employee Salary: " + pte.calculateSalary());
            System.out.println("Trainee Salary: " + trainee.calculateSalary());

            // Test exceptions
            PartTimeEmployee pte2 = new PartTimeEmployee("E004", "David", "Williams",
                    LocalDate.of(1992, 11, 2), "david@example.com",
                    LocalDate.of(2022, 1, 15), 18.0);
            try {
                System.out.println("PartTimeEmployee without hours: " + pte2.calculateSalary());
            } catch (MissingInformationException e) {
                logger.warning("Exception caught: " + e.getMessage());
                System.out.println("Exception: " + e.getMessage());
            }

            Trainee trainee2 = new Trainee("E005", "Eve", "Davis",
                    LocalDate.of(1999, 3, 12), "eve@example.com",
                    LocalDate.of(2024, 2, 1));
            try {
                trainee2.setScore(1.5);  // Invalid score
            } catch (InvalidArgumentException e) {
                logger.warning("Exception caught: " + e.getMessage());
                System.out.println("Exception: " + e.getMessage());
            }

            logger.info("System stop");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            logger.severe("Unexpected exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void setupLogger() throws IOException {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);

        // File handler logs all messages to a file
        FileHandler fileHandler = new FileHandler("system.log", true);
        fileHandler.setLevel(Level.ALL);
        fileHandler.setFormatter(new SimpleFormatter());

        logger.addHandler(fileHandler);

        // Console handler for convenience
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.INFO);
        logger.addHandler(consoleHandler);
    }
}
