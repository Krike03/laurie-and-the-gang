package org.laurieandthegang.parkshark.api.dto.people;

import com.sun.istack.NotNull;
import org.laurieandthegang.parkshark.api.dto.address.AddressDto;

public record CreateMemberDto(
        @NotNull NameDto name,
        @NotNull AddressDto address,
        @NotNull String phoneNumber,
        @NotNull String email,
        @NotNull LicensePlateDto licensePlate) {
}
