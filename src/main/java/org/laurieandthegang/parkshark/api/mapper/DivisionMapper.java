package org.laurieandthegang.parkshark.api.mapper;

import org.laurieandthegang.parkshark.api.dto.CreateDivisionDTO;
import org.laurieandthegang.parkshark.api.dto.DivisionDto;
import org.laurieandthegang.parkshark.domain.division.Division;
import org.springframework.stereotype.Component;

@Component
public class DivisionMapper {
    public Division mapper(CreateDivisionDTO createDivisionDTO) {
        return new Division(createDivisionDTO.name(),
                createDivisionDTO.originalName(),
                createDivisionDTO.director());
    }

    public DivisionDto mapper(Division division) {
        return new DivisionDto(division.getId(),
                division.getName(),
                division.getOriginalName(),
                division.getDirector());
    }
}
