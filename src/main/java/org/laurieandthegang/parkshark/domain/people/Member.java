package org.laurieandthegang.parkshark.domain.people;

import com.sun.istack.NotNull;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "MEMBER")
public class Member {

    //todo update the name, sequence name and generator name
    @Id
    @SequenceGenerator(name = "MEMBER_SEQ", sequenceName = "member_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ")
    private int id;

    @Embedded
    private Name name;

    @Embedded
    private Address address;

    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Embedded
    private LicensePlate licensePlate;

    @Column(name = "REGISTRATION_DATE")
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

    public String getPhoneNumber() {
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

    public Member( Name name, Address address, String phoneNumber, String email, LicensePlate licensePlate) {
//        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.licensePlate = licensePlate;
        this.registrationDate = LocalDate.now(); // todo update based on the link provided in the story
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
