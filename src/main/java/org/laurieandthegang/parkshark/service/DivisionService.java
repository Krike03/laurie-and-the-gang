package org.laurieandthegang.parkshark.service;

import org.laurieandthegang.parkshark.api.dto.CreateDivisionDTO;
import org.laurieandthegang.parkshark.api.dto.DivisionDto;
import org.laurieandthegang.parkshark.api.mapper.DivisionMapper;
import org.laurieandthegang.parkshark.domain.division.Division;
import org.laurieandthegang.parkshark.repository.DivisionRepository;
import org.springframework.stereotype.Service;

@Service
public class DivisionService {

    private DivisionRepository divisionRepository;
    private DivisionMapper divisionMapper;

    public DivisionService(DivisionRepository divisionRepository, DivisionMapper divisionMapper) {
        this.divisionRepository = divisionRepository;
        this.divisionMapper = divisionMapper;
    }

    public DivisionDto addDivision(CreateDivisionDTO createDivisionDTO){
        Division division = divisionMapper.mapper(createDivisionDTO);
        divisionRepository.insertDivision(division);
        return divisionMapper.mapper(division);
    }

}
