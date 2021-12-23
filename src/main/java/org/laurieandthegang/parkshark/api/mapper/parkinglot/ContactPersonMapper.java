package org.laurieandthegang.parkshark.api.mapper.parkinglot;

import org.laurieandthegang.parkshark.api.dto.parkinglot.ContactPersonDto;
import org.laurieandthegang.parkshark.api.mapper.address.AddressMapper;
import org.laurieandthegang.parkshark.api.mapper.people.NameMapper;
import org.laurieandthegang.parkshark.domain.parkinglot.ContactPerson;
-import org.springframework.stereotype.Component;

@Component
public class ContactPersonMapper {
    private final NameMapper nameMapper;
    private final AddressMapper addressMapper;

    public ContactPersonMapper(NameMapper nameMapper, AddressMapper addressMapper) {
        this.nameMapper = nameMapper;
        this.addressMapper = addressMapper;
    }

    public ContactPersonDto mapper(ContactPerson contactPerson) {
        return new ContactPersonDto(
                nameMapper.mapper(contactPerson.getName()),
                contactPerson.getEmail(),
                contactPerson.getMobilePhoneNumber(),
                contactPerson.getTelephoneNumber(),
                addressMapper.mapper(contactPerson.getAddress())
        );
    }

    public ContactPerson mapper(ContactPersonDto contactPersonDto) {
        return new ContactPerson(
                nameMapper.mapper(contactPersonDto.name()),
                contactPersonDto.email(),
                contactPersonDto.mobilePhoneNumber(),
                contactPersonDto.telephoneNumber(),
                addressMapper.mapper(contactPersonDto.addressDto()));
    }
}
