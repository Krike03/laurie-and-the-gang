package org.laurieandthegang.parkshark.api.dto.parkinglot;

import org.laurieandthegang.parkshark.api.dto.AddressDto;
import org.laurieandthegang.parkshark.domain.parkinglot.Category;
import org.laurieandthegang.parkshark.domain.parkinglot.ContactPerson;
import org.laurieandthegang.parkshark.domain.people.Address;

public record ParkingLotDto(
        int id,
        String name,
        Category category,
        int capacity,
        ContactPersonDto contactPerson,
        AddressDto address,
        double pricePerHour) {
}
