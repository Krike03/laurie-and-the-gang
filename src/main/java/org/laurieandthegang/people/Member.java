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

    private Address address;
    private PhoneNumber phoneNumber;
    private String email;
    private LicensePlate licensePlate;
    private LocalDate registrationDate;

    public Member() {
    }




}
