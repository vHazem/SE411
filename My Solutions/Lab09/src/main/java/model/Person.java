package model;
import java.time.LocalDate;

public abstract class Person {
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String email;

    public Person(String id, String firstName, String lastName, LocalDate dob, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
    }

    // Getters and setters

    public String getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public LocalDate getDob() { return dob; }
    public String getEmail() { return email; }
}
