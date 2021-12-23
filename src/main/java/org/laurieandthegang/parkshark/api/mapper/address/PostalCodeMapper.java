package org.laurieandthegang.parkshark.api.mapper.address;

import org.laurieandthegang.parkshark.api.dto.address.PostalCodeDto;
import org.laurieandthegang.parkshark.domain.people.PostalCode;
import org.springframework.stereotype.Component;

@Component
public class PostalCodeMapper {
    public PostalCode mapper(PostalCodeDto postalCodeDto) {
        return new PostalCode(
                postalCodeDto.numeralCode()
                , postalCodeDto.cityLabel());
    }
}
