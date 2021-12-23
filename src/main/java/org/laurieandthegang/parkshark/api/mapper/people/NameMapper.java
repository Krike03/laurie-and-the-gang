package org.laurieandthegang.parkshark.api.mapper.people;

import org.laurieandthegang.parkshark.api.dto.people.NameDto;
import org.laurieandthegang.parkshark.domain.people.Name;
import org.springframework.stereotype.Component;

@Component
public class NameMapper {

    public NameDto mapper(Name name) {
        return new NameDto(name.getFirstName(),
                name.getLastName());
    }

    public Name mapper(NameDto nameDto){
        return new Name(nameDto.firstName(),
                nameDto.lastName());
    }

}
