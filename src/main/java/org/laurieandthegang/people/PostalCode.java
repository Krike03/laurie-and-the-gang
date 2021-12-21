package org.laurieandthegang.people;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "POSTALCODE")
public class PostalCode {
    @Id
    @Column(name = "NUMERALCODE")
    private String numeralCode;

    @Column(name = "CITYLABEL")
    private String cityLabel;

    public PostalCode() {
    }

    public String getNumeralCode() {
        return numeralCode;
    }

    public String getCityLabel() {
        return cityLabel;
    }

    @Override
    public String toString() {
        return numeralCode + " " + cityLabel;
    }
}
