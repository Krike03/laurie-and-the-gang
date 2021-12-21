package org.laurieandthegang.parkshark.api.dto;

import org.laurieandthegang.parkshark.domain.people.Address;
import org.laurieandthegang.parkshark.domain.people.LicensePlate;
import org.laurieandthegang.parkshark.domain.people.Name;

public record CreateMemberDto(
        Name name,
        Address address,
        String phoneNumber,
        String email,
        LicensePlate licensePlate) {
}
