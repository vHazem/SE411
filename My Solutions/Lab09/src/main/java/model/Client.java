package model;

import java.time.LocalDate;

public class Client extends Person {
    public enum ContactMethod { EMAIL, PHONE, WHATSAPP }

    private ContactMethod preferredContactMethod;
    private String phoneNumber;

    public Client(String id, String firstName, String lastName, LocalDate dob, String email,
                  ContactMethod preferredContactMethod, String phoneNumber) {
        super(id, firstName, lastName, dob, email);
        this.preferredContactMethod = preferredContactMethod;
        this.phoneNumber = phoneNumber;
    }

    public ContactMethod getPreferredContactMethod() { return preferredContactMethod; }
    public String getPhoneNumber() { return phoneNumber; }
}
