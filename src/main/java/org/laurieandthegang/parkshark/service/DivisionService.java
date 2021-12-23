package org.laurieandthegang.parkshark.service;

import org.laurieandthegang.parkshark.api.dto.parkinglot.CreateDivisionDto;
import org.laurieandthegang.parkshark.api.dto.parkinglot.DivisionDto;
import org.laurieandthegang.parkshark.api.mapper.parkinglot.DivisionMapper;
import org.laurieandthegang.parkshark.domain.division.Division;
import org.laurieandthegang.parkshark.exception.RequiredFieldIsNullException;
import org.laurieandthegang.parkshark.repository.DivisionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DivisionService {

    private DivisionRepository divisionRepository;
    private DivisionMapper divisionMapper;

    public DivisionService(DivisionRepository divisionRepository, DivisionMapper divisionMapper) {
        this.divisionRepository = divisionRepository;
        this.divisionMapper = divisionMapper;
    }

    public DivisionDto addDivision(CreateDivisionDto createDivisionDto) {
        Division parentDivision = null;
        if(createDivisionDto.parentId() != null) {
            parentDivision = divisionRepository.getDivisionById(createDivisionDto.parentId());
        }
        validateRequiredFieldsNotNull(createDivisionDto);
        Division division = divisionMapper.mapper(createDivisionDto, parentDivision);
        divisionRepository.insertDivision(division);
        return divisionMapper.mapper(division);
    }

    public void validateRequiredFieldsNotNull(CreateDivisionDto createDivisionDTO) {
        if (createDivisionDTO.name() == null) {
            throw new RequiredFieldIsNullException("NAME");
        } else if (createDivisionDTO.originalName() == null) {
            throw new RequiredFieldIsNullException("ORIGINAL NAME");
        } else if (createDivisionDTO.director() == null) {
            throw new RequiredFieldIsNullException("DIRECTOR");
        }
    }

    public List<DivisionDto> getAllDivisions(){
        return divisionRepository.getAllDivisions().stream()
                .map(division -> divisionMapper.mapper(division))
                .collect(Collectors.toList());
    }
}
