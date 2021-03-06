package org.laurieandthegang.parkshark.domain.people;


import javax.persistence.*;
import java.util.Objects;

@Embeddable
public class Address {

    @Column(name = "STREET_NAME", nullable = false)
    private String streetName;

    @Column(name = "STREET_NUMBER", nullable = false)
    private String streetNumber;

    @Embedded
    private PostalCode postalCode;


    private Address() {
    }

    public Address(String streetName, String streetNumber, PostalCode postalCode) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public PostalCode getPostalCode() {
        return postalCode;
    }

    @Override
    public String toString() {
        return streetName + " " + streetNumber + " " + postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address)) return false;
        return Objects.equals(streetName, address.streetName) && Objects.equals(streetNumber, address.streetNumber) && Objects.equals(postalCode, address.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetName, streetNumber, postalCode);
    }
}

