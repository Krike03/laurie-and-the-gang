package org.laurieandthegang.parkshark.api.mapper.address;

import org.laurieandthegang.parkshark.api.dto.address.AddressDto;
import org.laurieandthegang.parkshark.domain.people.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    private final PostalCodeMapper postalCodeMapper;

    @Autowired
    public AddressMapper(PostalCodeMapper postalCodeMapper) {
        this.postalCodeMapper = postalCodeMapper;
    }

    public AddressDto mapper(Address address) {
        return new AddressDto(address.getStreetName(),
                address.getStreetNumber(),
                postalCodeMapper.mapper(address.getPostalCode()));
    }

    public Address mapper(AddressDto addressDto) {
        return new Address(
                addressDto.streetName(),
                addressDto.streetNumber(),
                postalCodeMapper.mapper(addressDto.postalCode()));
    }
}
