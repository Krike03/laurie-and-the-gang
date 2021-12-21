package org.laurieandthegang.people;

import javax.persistence.*;

@Embeddable
public class Address {

    @Column(name = "STREET_NAME")
    private String streetName;

    @Column(name = "STREET_NUMBER")
    private String streetNumber; //could be 11A


    @ManyToOne
    @JoinColumn(name = "NUMERAL_CODE") // should be the same as PostalCode.java id
    private PostalCode postalCode; // could be 9000B


    private Address() {
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
}

