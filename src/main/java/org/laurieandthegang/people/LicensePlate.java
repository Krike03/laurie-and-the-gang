package org.laurieandthegang.people;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class LicensePlate {

    @Id
    @Column(name = "LICENSE_NUMBER")
    private String licenseNumber;

    @Column(name = "COUNTRY_LABEL")
    private String countryLabel;

    public LicensePlate() {
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
}
