package org.laurieandthegang.parkshark.domain.people;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "POSTALCODE")
public class PostalCode {
    @Id
    @Column(name = "NUMERAL_CODE")
    private String numeralCode;

    @Column(name = "CITY_LABEL")
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
