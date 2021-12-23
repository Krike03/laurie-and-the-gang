package org.laurieandthegang.parkshark.api.dto.people;

import org.laurieandthegang.parkshark.api.dto.AddressDto;
import org.laurieandthegang.parkshark.domain.people.Address;
import org.laurieandthegang.parkshark.domain.people.LicensePlate;
import org.laurieandthegang.parkshark.domain.people.Name;

import java.time.LocalDate;

public record MemberDto(
        int id,
        NameDto name,
        AddressDto address,
        String phoneNumber,
        String email,
        LicensePlateDto licensePlate,
        LocalDate registrationDate) {


}
