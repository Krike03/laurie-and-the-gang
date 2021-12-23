package org.laurieandthegang.parkshark.api.dto.people;

import com.sun.istack.NotNull;
import org.laurieandthegang.parkshark.api.dto.AddressDto;
import org.laurieandthegang.parkshark.domain.people.Address;
import org.laurieandthegang.parkshark.domain.people.LicensePlate;
import org.laurieandthegang.parkshark.domain.people.Name;

public record CreateMemberDto(
        @NotNull NameDto name,
        @NotNull AddressDto address,
        @NotNull String phoneNumber,
        @NotNull String email,
        @NotNull LicensePlateDto licensePlate) {
}
