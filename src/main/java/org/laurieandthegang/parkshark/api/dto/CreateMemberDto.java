package org.laurieandthegang.parkshark.api.dto;

import com.sun.istack.NotNull;
import org.laurieandthegang.parkshark.domain.people.Address;
import org.laurieandthegang.parkshark.domain.people.LicensePlate;
import org.laurieandthegang.parkshark.domain.people.Name;

public record CreateMemberDto(
        @NotNull Name name,
        @NotNull Address address,
        @NotNull String phoneNumber,
        @NotNull String email,
        @NotNull LicensePlate licensePlate) {
}
