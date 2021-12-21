package org.laurieandthegang.parkshark.api.dto;

import org.apache.tomcat.jni.Address;
import org.laurieandthegang.parkshark.domain.people.LicensePlate;

import java.time.LocalDate;

public class MemberDto {

    private String firstName;
    private String lastName;
    private Address address;
    private String phoneNumber;
    private String email;
    private LicensePlate licensePlate;
    private LocalDate registrationDate;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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

    @Override
    public String toString() {
        return "MemberDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address=" + address +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", licensePlate=" + licensePlate +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
