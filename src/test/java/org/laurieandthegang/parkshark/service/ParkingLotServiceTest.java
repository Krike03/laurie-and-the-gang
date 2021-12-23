package org.laurieandthegang.parkshark.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.laurieandthegang.parkshark.api.dto.parkinglot.CreateParkingLotDto;
import org.laurieandthegang.parkshark.api.dto.parkinglot.ParkingLotDto;
import org.laurieandthegang.parkshark.api.mapper.address.AddressMapper;
import org.laurieandthegang.parkshark.api.mapper.address.PostalCodeMapper;
import org.laurieandthegang.parkshark.api.mapper.parkinglot.ContactPersonMapper;
import org.laurieandthegang.parkshark.api.mapper.parkinglot.ParkingLotMapper;
import org.laurieandthegang.parkshark.api.mapper.people.LicensePlateMapper;
import org.laurieandthegang.parkshark.api.mapper.people.NameMapper;
import org.laurieandthegang.parkshark.domain.parkinglot.Category;
import org.laurieandthegang.parkshark.domain.parkinglot.ContactPerson;
import org.laurieandthegang.parkshark.domain.parkinglot.ParkingLot;
import org.laurieandthegang.parkshark.domain.people.Address;
import org.laurieandthegang.parkshark.domain.people.Name;
import org.laurieandthegang.parkshark.domain.people.PostalCode;
import org.laurieandthegang.parkshark.repository.ParkingLotRepository;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

public class ParkingLotServiceTest {

    private ParkingLotRepository mockParkingLotRepository;
    private ParkingLotService parkingLotService;
    private ParkingLotMapper parkingLotMapper;
    private ContactPersonMapper contactPersonMapper;
    private AddressMapper addressMapper;
    private PostalCodeMapper postalCodeMapper;
    private NameMapper nameMapper;
    private LicensePlateMapper licensePlateMapper;

    private ContactPerson contactPerson;
    private PostalCode postalCode;
    private Address address;
    private CreateParkingLotDto createParkingLotDto;


    @BeforeEach
    void setUp() {
        mockParkingLotRepository = Mockito.mock(ParkingLotRepository.class);
        nameMapper = new NameMapper();
        licensePlateMapper = new LicensePlateMapper();
        postalCodeMapper = new PostalCodeMapper();
        addressMapper = new AddressMapper(postalCodeMapper);
        contactPersonMapper = new ContactPersonMapper(nameMapper, addressMapper);
        parkingLotMapper = new ParkingLotMapper(contactPersonMapper, addressMapper);
        parkingLotService = new ParkingLotService(mockParkingLotRepository, parkingLotMapper,
                new Validator(addressMapper,
                        licensePlateMapper,
                        contactPersonMapper));

        Address contactPersonsAddress = new Address("Sesams", "123", new PostalCode("123", "BE"));
        contactPerson = new ContactPerson(new Name("Tims", "second mistake"), "tim@2ndmistake.org", "2- 1 - 11 en de rest zoekte zelf", null, contactPersonsAddress);
        postalCode = new PostalCode("9000", "Gent");
        address = new Address("Sint-Pieters NieuwStraat", "8964", postalCode);


        createParkingLotDto = new CreateParkingLotDto(
                "parking name",
                Category.UNDERGROUND,
                100,
                contactPersonMapper.mapper(contactPerson),
                addressMapper.mapper(contactPersonsAddress),
                1.12);
    }

    @Test
    void givenAMock_whenRegisteringAParkingLot_thenHaveTheParkingLotAdded() {
        //GIVEN
        ParkingLot parkingLot = parkingLotMapper.mapper(createParkingLotDto);
        Mockito.doNothing().when(mockParkingLotRepository).addParkingLot(any());

        //WHEN
        ParkingLotDto parkingLotDto = parkingLotService.addParkingLot(createParkingLotDto);

        //THEN
        Assertions.assertThat(parkingLotDto).isEqualTo(parkingLotMapper.mapper(parkingLot));

    }
}
