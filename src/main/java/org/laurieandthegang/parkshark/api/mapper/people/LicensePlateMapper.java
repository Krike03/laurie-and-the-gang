package org.laurieandthegang.parkshark.api.mapper.people;

import org.laurieandthegang.parkshark.api.dto.people.LicensePlateDto;
import org.laurieandthegang.parkshark.domain.people.LicensePlate;
import org.springframework.stereotype.Component;

@Component
public class LicensePlateMapper {
    public LicensePlateDto mapper(LicensePlate licensePlate) {
        return new LicensePlateDto(
                licensePlate.getLicenseNumber(),
                licensePlate.getCountryLabel()
        );
    }

    public LicensePlate mapper(LicensePlateDto licensePlateDto) {
        return new LicensePlate(licensePlateDto.licensePlateNumber(),
                licensePlateDto.licePlateLabel());
    }
}
