package org.laurieandthegang.parkshark.api.dto.people;

import org.laurieandthegang.parkshark.api.dto.address.AddressDto;

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
