package org.laurieandthegang.parkshark.domain.parkinglot;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "contact_person")
public class ContactPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "MOBILE_PHONE_NUMBER")
    private String mobilePhoneNumber;

    @Column(name = "TELEPHONE_NUMBER")
    private String telephoneNumber;

    public String getEmail() {
        return email;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }


    public String getTelephoneNumber() {
        return telephoneNumber;
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