package org.laurieandthegang.parkshark.domain.parkinglot;

import org.laurieandthegang.parkshark.domain.people.Address;
import org.laurieandthegang.parkshark.domain.people.Name;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "contact_person")
public class ContactPerson {
    @Id
    @SequenceGenerator(name = "CONTACT_PERSON_SEQ", sequenceName = "contact_person_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTACT_PERSON_SEQ")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Embedded
    private Name name;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "MOBILE_PHONE_NUMBER")
    private String mobilePhoneNumber;

    @Column(name = "TELEPHONE_NUMBER")
    private String telephoneNumber;

    @Embedded
    private Address address;

    public String getEmail() {
        return email;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }


    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public ContactPerson(Name name, String email, String mobilePhoneNumber, String telephoneNumber, Address address) {
        this.name = name;
        this.email = email;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.telephoneNumber = telephoneNumber;
        this.address = address;
    }

    private ContactPerson() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactPerson that = (ContactPerson) o;
        return Objects.equals(id, that.id) && Objects.equals(email, that.email) && Objects.equals(mobilePhoneNumber, that.mobilePhoneNumber) && Objects.equals(telephoneNumber, that.telephoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, mobilePhoneNumber, telephoneNumber);
    }

    @Override
    public String toString() {
        return "ContactPerson{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", mobilePhoneNumber='" + mobilePhoneNumber + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                '}';
    }
}