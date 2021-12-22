package org.laurieandthegang.parkshark.api.mapper;

import org.laurieandthegang.parkshark.api.dto.CreateDivisionDto;
import org.laurieandthegang.parkshark.api.dto.DivisionDto;
import org.laurieandthegang.parkshark.domain.division.Division;
import org.springframework.stereotype.Component;

@Component
public class DivisionMapper {
    public Division mapper(CreateDivisionDto createDivisionDTO) {
        return new Division(createDivisionDTO.name(),
                createDivisionDTO.originalName(),
                createDivisionDTO.director());
    }

    public DivisionDto mapper(Division division) {
        DivisionDto subDivisionDto;
        if(division.getDivision() == null){
            subDivisionDto = null;
        }else {
            subDivisionDto = mapper(division.getDivision());
        }
        return new DivisionDto(division.getId(),
                division.getName(),
                division.getOriginalName(),
                division.getDirector(),
                subDivisionDto);
    }
}
