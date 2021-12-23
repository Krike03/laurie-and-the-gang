package org.laurieandthegang.parkshark.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.laurieandthegang.parkshark.api.dto.parkinglot.CreateDivisionDto;
import org.laurieandthegang.parkshark.api.dto.parkinglot.DivisionDto;
import org.laurieandthegang.parkshark.api.mapper.DivisionMapper;
import org.laurieandthegang.parkshark.domain.division.Division;
import org.laurieandthegang.parkshark.exception.RequiredFieldIsNullException;
import org.laurieandthegang.parkshark.repository.DivisionRepository;
import org.mockito.Mockito;

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
                "Director name",
                null);
        Division division = new Division(createDivisionDto.name(), createDivisionDto.originalName(), createDivisionDto.director(), null);
        Mockito.doNothing().when(mockDivisionRepository).insertDivision(any());
        //WHEN
        DivisionDto divisionDto = divisionService.addDivision(createDivisionDto);
        //THEN
        Assertions.assertThat(divisionDto).isEqualTo(divisionMapper.mapper(division));
    }

    @Test
    void givenDivisionToCreate_whenRegisteringDivisionNameIncorrectly_thenThrowError() {
        //GIVEN
        //WHEN
        CreateDivisionDto createDivisionDto = new CreateDivisionDto(null,"","", null);
        //THEN
        Assertions.assertThatExceptionOfType(RequiredFieldIsNullException.class).isThrownBy(()-> divisionService.addDivision(createDivisionDto));
    }

    @Test
    void givenDivisionToCreate_whenRegisteringDivisionOriginalNameIncorrectly_thenThrowError() {
        //GIVEN
        //WHEN
        CreateDivisionDto createDivisionDto = new CreateDivisionDto("",null,"", null);
        //THEN
        Assertions.assertThatExceptionOfType(RequiredFieldIsNullException.class).isThrownBy(()-> divisionService.addDivision(createDivisionDto));
    }

    @Test
    void givenDivisionToCreate_whenRegisteringDivisionDirectorIncorrectly_thenThrowError() {
        //GIVEN
        //WHEN
        CreateDivisionDto createDivisionDto = new CreateDivisionDto("","",null, null);
        //THEN
        Assertions.assertThatExceptionOfType(RequiredFieldIsNullException.class).isThrownBy(()-> divisionService.addDivision(createDivisionDto));
    }
}