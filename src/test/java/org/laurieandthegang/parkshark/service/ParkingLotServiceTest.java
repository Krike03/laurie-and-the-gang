package org.laurieandthegang.parkshark.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.laurieandthegang.parkshark.api.dto.CreateParkingLotDto;
import org.laurieandthegang.parkshark.api.dto.ParkingLotDto;
import org.laurieandthegang.parkshark.api.mapper.ParkingLotMapper;
import org.laurieandthegang.parkshark.domain.parkinglot.Category;
import org.laurieandthegang.parkshark.domain.parkinglot.ContactPerson;
import org.laurieandthegang.parkshark.domain.parkinglot.ParkingLot;
import org.laurieandthegang.parkshark.domain.people.Address;
import org.laurieandthegang.parkshark.domain.people.PostalCode;
import org.laurieandthegang.parkshark.repository.ParkingLotRepository;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;


public class ParkingLotServiceTest {

    private ParkingLotRepository mockParkingLotRepository;
    private ParkingLotService parkingLotService;
    private ParkingLotMapper parkingLotMapper;

    private ContactPerson contactPerson;
    private PostalCode postalCode;
    private Address address;
    private CreateParkingLotDto createParkingLotDto;


    @BeforeEach
    void setUp() {
        mockParkingLotRepository = Mockito.mock(ParkingLotRepository.class);
        parkingLotMapper = new ParkingLotMapper();
        parkingLotService = new ParkingLotService(mockParkingLotRepository, parkingLotMapper);
        contactPerson = new ContactPerson();
        postalCode = new PostalCode("9000", "Gent");
        address = new Address("Sint-Pieters NieuwStraat", "8964", postalCode);
        createParkingLotDto = new CreateParkingLotDto("NameParking", 563, address, Category.UNDERGROUND, contactPerson, 9.35);
    }

    @Test
    void name() {
        //GIVEN
        ParkingLot parkingLot = parkingLotMapper.mapper(createParkingLotDto);
        Mockito.doNothing().when(mockParkingLotRepository).addParkingLot(any());

        //WHEN
        ParkingLotDto parkingLotDto = parkingLotService.addParkingLot(createParkingLotDto);

        //THEN
        Assertions.assertThat(parkingLotDto).isEqualTo(parkingLotMapper.mapper(parkingLot));

    }
}
