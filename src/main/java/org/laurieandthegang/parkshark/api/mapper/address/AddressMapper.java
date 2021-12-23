package org.laurieandthegang.parkshark.api.mapper.address;

import org.laurieandthegang.parkshark.api.dto.address.AddressDto;
import org.laurieandthegang.parkshark.domain.people.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    private final PostalCodeMapper postalCodeMapper;

    public AddressMapper(PostalCodeMapper postalCodeMapper) {
        this.postalCodeMapper = postalCodeMapper;
    }

    public Address mapper(AddressDto addressDto) {
        return new Address(
                addressDto.streetName(),
                addressDto.streetNumber(),
                postalCodeMapper.mapper(addressDto.postalCode()));
    }
}
