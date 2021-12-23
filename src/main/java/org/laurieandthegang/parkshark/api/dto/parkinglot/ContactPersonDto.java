package org.laurieandthegang.parkshark.api.dto.parkinglot;

import org.laurieandthegang.parkshark.api.dto.AddressDto;
import org.laurieandthegang.parkshark.api.dto.people.NameDto;

public record ContactPersonDto (NameDto name, String email, String mobilePhoneNumber, String telephoneNumber, AddressDto addressDto){
}
