package org.laurieandthegang.people;

import javax.persistence.*;

@Embeddable
public class Address {

    @Column(name = "STREETNAME")
    private String streetName;

    @Column(name = "STREETNUMBER")
    private String streetNumber; //could be 11A


    @ManyToOne
    private PostalCode postalCode; // could be 9000B


    private Address() {
    }
}
