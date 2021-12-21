package org.laurieandthegang.parkshark.domain.people;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.util.Objects;

@Embeddable
public class LicensePlate {

    @Column(name = "LICENSE_NUMBER")
    private String licenseNumber;

    @Column(name = "COUNTRY_LABEL")
    private String countryLabel;

    private LicensePlate() {
    }

    public LicensePlate(String licenseNumber, String countryLabel) {
        this.licenseNumber = licenseNumber;
        this.countryLabel = countryLabel;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getCountryLabel() {
        return countryLabel;
    }

    @Override
    public String toString() {
        return licenseNumber + " " + countryLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LicensePlate that)) return false;
        return Objects.equals(licenseNumber, that.licenseNumber) && Objects.equals(countryLabel, that.countryLabel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(licenseNumber, countryLabel);
    }
}
