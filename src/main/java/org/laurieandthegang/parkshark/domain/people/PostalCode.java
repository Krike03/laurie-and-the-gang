package org.laurieandthegang.parkshark.domain.people;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
public class PostalCode {

    @Column(name = "NUMERAL_CODE")
    private String numeralCode;

    @Column(name = "CITY_LABEL")
    private String cityLabel;

    private PostalCode() {
    }

    public PostalCode(String numeralCode, String cityLabel) {
        this.numeralCode = numeralCode;
        this.cityLabel = cityLabel;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostalCode that)) return false;
        return Objects.equals(numeralCode, that.numeralCode) && Objects.equals(cityLabel, that.cityLabel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeralCode, cityLabel);
    }
}
