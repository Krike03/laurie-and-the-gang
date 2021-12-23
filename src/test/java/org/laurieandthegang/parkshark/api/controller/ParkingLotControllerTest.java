package org.laurieandthegang.parkshark.api.controller;

import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.laurieandthegang.parkshark.api.dto.parkinglot.CreateParkingLotDto;
import org.laurieandthegang.parkshark.api.dto.parkinglot.ParkingLotDto;
import org.laurieandthegang.parkshark.api.dto.parkinglot.RestrictedParkingLotDto;
import org.laurieandthegang.parkshark.api.mapper.address.AddressMapper;
import org.laurieandthegang.parkshark.api.mapper.parkinglot.ContactPersonMapper;
import org.laurieandthegang.parkshark.api.mapper.parkinglot.ParkingLotMapper;
import org.laurieandthegang.parkshark.domain.parkinglot.Category;
import org.laurieandthegang.parkshark.domain.parkinglot.ContactPerson;
import org.laurieandthegang.parkshark.domain.people.Address;
import org.laurieandthegang.parkshark.domain.people.Name;
import org.laurieandthegang.parkshark.domain.people.PostalCode;
import org.laurieandthegang.parkshark.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class ParkingLotControllerTest {
    @LocalServerPort
    private int port;

    private ContactPersonMapper contactPersonMapper;
    private AddressMapper addressMapper;
    private ParkingLotService parkingLotService;
    private ParkingLotMapper parkingLotMapper;


    private ContactPerson contactPerson;
    private PostalCode postalCode;
    private Address address;
    private CreateParkingLotDto createParkingLotDto;

    @Autowired
    public ParkingLotControllerTest(ContactPersonMapper contactPersonMapper, AddressMapper addressMapper, ParkingLotService parkingLotService, ParkingLotMapper parkingLotMapper) {
        this.contactPersonMapper = contactPersonMapper;
        this.addressMapper = addressMapper;
        this.parkingLotService = parkingLotService;
        this.parkingLotMapper = parkingLotMapper;
    }


    @BeforeAll
    void setUp() {
        Address contactPersonsAddress = new Address("Sesam", "123", new PostalCode("123", "BE"));

        contactPerson = new ContactPerson(
                new Name("Tims", "mistake"),
                "tim@mistake.org",
                "2- 1 - 11 en de rest zoekte zelf",
                null,
                contactPersonsAddress);

        postalCode = new PostalCode("9000", "Gent");
        address = new Address("Sint-Pieters NieuwStraat",
                "8964",
                postalCode);


        createParkingLotDto = new CreateParkingLotDto(
                "parking name",
                Category.UNDERGROUND,
                100,
                contactPersonMapper.mapper(contactPerson),
                addressMapper.mapper(address),
                1.12);

    }

    @Test
    public void givenAParkingLotToCreate_whenAddingParkingLot_thenParkingLotIsAdded() {
        //GIVEN

        //WHEN
        ParkingLotDto parkingLotDto = RestAssured
                .given()
                .body(createParkingLotDto)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/parkinglots")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(ParkingLotDto.class);

        //THEN
        assertThat(parkingLotDto.name()).isEqualTo(createParkingLotDto.name());
        assertThat(parkingLotDto.capacity()).isEqualTo(createParkingLotDto.capacity());
        assertThat(parkingLotDto.address()).isEqualTo(createParkingLotDto.address());
        assertThat(parkingLotDto.category()).isEqualTo(createParkingLotDto.category());
        assertThat(parkingLotDto.contactPerson()).isEqualTo(createParkingLotDto.contactPerson());
        assertThat(parkingLotDto.pricePerHour()).isEqualTo(createParkingLotDto.pricePerHour());

    }
//
//    @Test
//    void givenParkingLotsInDatabase_whenGettingAllParkingLots_thenAllParkingLotsAreReturned() {
//        CreateParkingLotDto createParkingLotDto1 = new CreateParkingLotDto(
//                "Parking lot name",
//                Category.UNDERGROUND,
//                150,
//                contactPersonMapper.mapper(contactPerson),
//                addressMapper.mapper(address),
//                5.0);
//
//        CreateParkingLotDto createParkingLotDto2 = new CreateParkingLotDto(
//                "Parking lot name 2",
//                Category.ABOVE_GROUND,
//                150,
//                contactPersonMapper.mapper(contactPerson),
//                addressMapper.mapper(address),
//                5.0);
//
//        ParkingLotDto parkingLotDto1 = parkingLotService.addParkingLot(createParkingLotDto1);
//        ParkingLotDto parkingLotDto2 = parkingLotService.addParkingLot(createParkingLotDto2);
//
//        List<RestrictedParkingLotDto> restrictedParkingLotDtoList = RestAssured
//                .given()
//                .contentType(JSON)
//                .when()
//                .port(port)
//                .get("/parkinglots")
//                .then()
//                .assertThat()
//                .statusCode(HttpStatus.OK.value())
//                .extract()
//                .jsonPath().getList(".", RestrictedParkingLotDto.class);
//
//        System.out.println(restrictedParkingLotDtoList);
//
//        RestrictedParkingLotDto restricted1 = parkingLotMapper.mapEntityToTheRestrictedDto(parkingLotMapper.mapper(createParkingLotDto1));
//        RestrictedParkingLotDto restricted2 = parkingLotMapper.mapEntityToTheRestrictedDto(parkingLotMapper.mapper(createParkingLotDto2));
//
//        System.out.println(restricted1);
//        System.out.println(restricted2);
//        System.out.println("AAAAAAAAAAAAAAAAAA");
//        System.out.println("AAAAAAAAAAAAAAAAAA");
//        System.out.println("AAAAAAAAAAAAAAAAAA");
//        System.out.println("AAAAAAAAAAAAAAAAAA");
//
////        assertThat(restrictedParkingLotDtoList.contains((restricted1))).isTrue();
////        assertThat(restrictedParkingLotDtoList.contains((restricted2))).isTrue();
//
//
//        Assertions.assertThat(restrictedParkingLotDtoList.get(0).name()).isEqualTo(createParkingLotDto1.name());
//        Assertions.assertThat(restrictedParkingLotDtoList.get(0).capacity()).isEqualTo(createParkingLotDto1.capacity());
//        Assertions.assertThat(restrictedParkingLotDtoList.get(0).email()).isEqualTo(createParkingLotDto1.contactPerson().email());
//        Assertions.assertThat(restrictedParkingLotDtoList.get(0).telephone()).isEqualTo(createParkingLotDto1.contactPerson().telephoneNumber());
//
//        Assertions.assertThat(restrictedParkingLotDtoList.get(1).name()).isEqualTo(createParkingLotDto2.name());
//        Assertions.assertThat(restrictedParkingLotDtoList.get(1).capacity()).isEqualTo(createParkingLotDto2.capacity());
//        Assertions.assertThat(restrictedParkingLotDtoList.get(1).email()).isEqualTo(createParkingLotDto2.contactPerson().email());
//        Assertions.assertThat(restrictedParkingLotDtoList.get(1).telephone()).isEqualTo(createParkingLotDto2.contactPerson().telephoneNumber());
//
//
//    }
}
