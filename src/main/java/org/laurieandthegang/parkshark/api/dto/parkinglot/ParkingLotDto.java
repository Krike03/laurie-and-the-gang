package org.laurieandthegang.parkshark.api.dto.parkinglot;

import org.laurieandthegang.parkshark.api.dto.address.AddressDto;
import org.laurieandthegang.parkshark.domain.parkinglot.Category;

public record ParkingLotDto(
        int id,
        String name,
        Category category,
        int capacity,
        ContactPersonDto contactPerson,
        AddressDto address,
        double pricePerHour) {
}
