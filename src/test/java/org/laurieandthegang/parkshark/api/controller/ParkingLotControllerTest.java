package org.laurieandthegang.parkshark.api.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.laurieandthegang.parkshark.api.dto.parkinglot.CreateParkingLotDto;
import org.laurieandthegang.parkshark.api.dto.parkinglot.ParkingLotDto;
import org.laurieandthegang.parkshark.api.mapper.address.AddressMapper;
import org.laurieandthegang.parkshark.api.mapper.parkinglot.ContactPersonMapper;
import org.laurieandthegang.parkshark.domain.parkinglot.Category;
import org.laurieandthegang.parkshark.domain.parkinglot.ContactPerson;
import org.laurieandthegang.parkshark.domain.people.Address;
import org.laurieandthegang.parkshark.domain.people.Name;
import org.laurieandthegang.parkshark.domain.people.PostalCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class ParkingLotControllerTest {
    @LocalServerPort
    private int port;

    private String url;
    private String keyCloackToken;

    @Autowired
    private ContactPersonMapper contactPersonMapper;

    @Autowired
    private AddressMapper addressMapper;

    private ContactPerson contactPerson;
    private PostalCode postalCode;
    private Address address;
    private CreateParkingLotDto createParkingLotDto;




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

        url = "https://keycloak.switchfully.com/auth/realms/java-oct-2021/protocol/openid-connect/token";

        keyCloackToken = RestAssured
                .given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("grant_type", "password")
                .formParam("username", "manager1")
                .formParam("password", "password")
                .formParam("client_id", "parkshark")
                .when()
                .post(url)
                .then()
                .extract()
                .path("access_token")
                .toString();

    }

    @Test
    public void givenAParkingLotToCreate_whenAddingParkingLot_thenParkingLotIsAdded() {
        //GIVEN

        //WHEN
        ParkingLotDto parkingLotDto = RestAssured
                .given()
                .auth()
                .oauth2(keyCloackToken)
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
}
