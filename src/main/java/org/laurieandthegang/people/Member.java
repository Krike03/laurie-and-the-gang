package org.laurieandthegang.people;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "MEMBER")
public class Member {

    //todo update the name, sequence name and generator name
    @Id
    @SequenceGenerator(name = "NAME", sequenceName = "SEQ IDK", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NAME_SEQ")
    private int id;

    @Embedded
    private Name name;

    @Embedded
    private Address address;

    @Embedded
    private PhoneNumber phoneNumber;

    @Column(name = "EMAIL")
    private String email;

    @Embedded
    private LicensePlate licensePlate;

    @Column(name = "REGISTRATIONDATE")
    private LocalDate registrationDate;

    public Member() {
    }

    public int getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id: " + id +
                ", name: " + name +
                ", address: " + address +
                ", phoneNumber: " + phoneNumber +
                ", email: " + email + '\'' +
                ", licensePlate: " + licensePlate +
                ", registrationDate: " + registrationDate +
                '}';
    }
}
