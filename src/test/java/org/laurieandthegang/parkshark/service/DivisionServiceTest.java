package org.laurieandthegang.parkshark.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.laurieandthegang.parkshark.api.dto.CreateDivisionDto;
import org.laurieandthegang.parkshark.api.dto.DivisionDto;
import org.laurieandthegang.parkshark.api.mapper.DivisionMapper;
import org.laurieandthegang.parkshark.domain.division.Division;
import org.laurieandthegang.parkshark.repository.DivisionRepository;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class DivisionServiceTest {

    private DivisionRepository mockDivisionRepository;
    private DivisionService divisionService;
    private DivisionMapper divisionMapper;

    @BeforeEach
    void setUp() {
        mockDivisionRepository = Mockito.mock(DivisionRepository.class);
        divisionMapper = new DivisionMapper();
        divisionService = new DivisionService(mockDivisionRepository, divisionMapper);

    }

    @Test
    void givenCreateDivisionDto_whenAddingDivision_thenRepositoryContainsDivisionDto() {
        //GIVEN
        CreateDivisionDto createDivisionDto = new CreateDivisionDto("Division name",
                "Division original name",
                "Director name");
        Division division = new Division(createDivisionDto.name(), createDivisionDto.originalName(), createDivisionDto.director());
        Mockito.doNothing().when(mockDivisionRepository).insertDivision(any());
        //WHEN
        DivisionDto divisionDto = divisionService.addDivision(createDivisionDto);
        //THEN
        Assertions.assertThat(divisionDto).isEqualTo(divisionMapper.mapper(division));
    }
}