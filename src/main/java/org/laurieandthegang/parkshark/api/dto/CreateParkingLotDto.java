package org.laurieandthegang.parkshark.api.dto;

import org.laurieandthegang.parkshark.domain.parkinglot.Category;
import org.laurieandthegang.parkshark.domain.parkinglot.ContactPerson;
import org.laurieandthegang.parkshark.domain.people.Address;

public record CreateParkingLotDto(String name, int capacity, Address address, Category category,
                                  ContactPerson contactPerson, double pricePerHour) {

}
