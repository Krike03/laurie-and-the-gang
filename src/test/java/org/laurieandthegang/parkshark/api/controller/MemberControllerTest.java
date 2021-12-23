package org.laurieandthegang.parkshark.api.controller;

import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.laurieandthegang.parkshark.api.dto.people.CreateMemberDto;
import org.laurieandthegang.parkshark.api.dto.people.MemberDto;
import org.laurieandthegang.parkshark.api.mapper.address.AddressMapper;
import org.laurieandthegang.parkshark.api.mapper.people.LicensePlateMapper;
import org.laurieandthegang.parkshark.api.mapper.people.NameMapper;
import org.laurieandthegang.parkshark.domain.people.Address;
import org.laurieandthegang.parkshark.domain.people.LicensePlate;
import org.laurieandthegang.parkshark.domain.people.Name;
import org.laurieandthegang.parkshark.domain.people.PostalCode;
import org.laurieandthegang.parkshark.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.OK;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class MemberControllerTest {

    @LocalServerPort
    private int port;


    private String response;
    private String url;

    @Autowired
    private NameMapper nameMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private LicensePlateMapper licensePlateMapper;

    @Autowired
    private MemberService memberService;

    private Name name;
    private PostalCode postalCode;
    private Address address;
    private LicensePlate licensePlate;

    @BeforeAll
    void setUp() {
        name = new Name("tim", "tom");
        postalCode = new PostalCode("toktg", "oghf");
        address = new Address("timtom", "tomtim", postalCode);
        licensePlate = new LicensePlate("shfdug", "België.");
        url = "https://keycloak.switchfully.com/auth/realms/java-oct-2021/protocol/openid-connect/token";

        response = RestAssured
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
    void givenMemberToCreate_whenRegisteringMemberCorrectly_thenAddMember() {
        CreateMemberDto createMemberDto = new CreateMemberDto(
                nameMapper.mapper(name),
                addressMapper.mapper(address),
                "557",
                "@number",
                licensePlateMapper.mapper(licensePlate));

        MemberDto memberDto = RestAssured
                .given()
                .body(createMemberDto)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/members")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(MemberDto.class);

        assertThat(memberDto.name()).isEqualTo(createMemberDto.name());
        assertThat(memberDto.address()).isEqualTo(createMemberDto.address());
        assertThat(memberDto.phoneNumber()).isEqualTo(createMemberDto.phoneNumber());
        assertThat(memberDto.email()).isEqualTo(createMemberDto.email());
        assertThat(memberDto.licensePlate()).isEqualTo(createMemberDto.licensePlate());
    }

    @Test
    void givenMemberToCreate_whenRegisteringStreetNameIncorrectly_thenThrowError() {
        Address faultyAddress = new Address(null, "jhfg", postalCode);
        CreateMemberDto createMemberDto = new CreateMemberDto(
                nameMapper.mapper(name),
                addressMapper.mapper(faultyAddress),
                "557",
                "@number",
                licensePlateMapper.mapper(licensePlate));

        RestAssured
                .given()
                .body(createMemberDto)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/members")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", Matchers.is("Required field ADDRESS - STREET NAME was null."));
    }

    @Test
    void givenMemberToCreate_whenRegisteringStreetNumberIncorrectly_thenThrowError() {
        Address faultyAddress = new Address("null xd", null, postalCode);
        CreateMemberDto createMemberDto = new CreateMemberDto(
                nameMapper.mapper(name),
                addressMapper.mapper(faultyAddress),
                "557", "@number",
                licensePlateMapper.mapper(licensePlate));
        RestAssured
                .given()
                .body(createMemberDto)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/members")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", Matchers.is("Required field ADDRESS - STREET NUMBER was null."));
    }

    @Test
    void givenMemberToCreate_whenRegisteringNumeralCodeIncorrectly_thenThrowError() {
        PostalCode faultyPostal = new PostalCode(null, "doizfh");
        Address faultyAddress = new Address("null xd", "Gent", faultyPostal);

        CreateMemberDto createMemberDto = new CreateMemberDto(nameMapper.mapper(name),
                addressMapper.mapper(faultyAddress), "557", "@number", licensePlateMapper.mapper(licensePlate));

        RestAssured
                .given()
                .auth()
                .oauth2(response)
                .body(createMemberDto)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/members")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", Matchers.is("Required field ADDRESS - POSTAL CODE - NUMERAL was null."));
    }

    @Test
    void givenMemberToCreate_whenRegisteringCityLabelIncorrectly_thenThrowError() {
        PostalCode faultyPostal = new PostalCode("null", null);
        Address faultyAddress = new Address("null xd", "Gent", faultyPostal);

        CreateMemberDto createMemberDto = new CreateMemberDto(nameMapper.mapper(name),
                addressMapper.mapper(faultyAddress), "557", "@number", licensePlateMapper.mapper(licensePlate));
        RestAssured
                .given()
                .body(createMemberDto)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/members")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", Matchers.is("Required field ADDRESS - POSTAL CODE - CITY LABEL was null."));
    }

    @Test
    void givenMemberToCreate_whenRegisteringPhoneNumberIncorrectly_thenThrowError() {
        CreateMemberDto createMemberDto = new CreateMemberDto(nameMapper.mapper(name),
                addressMapper.mapper(address), null, "@number", licensePlateMapper.mapper(licensePlate));

        RestAssured
                .given()
                .body(createMemberDto)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/members")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", Matchers.is("Required field PHONE NUMBER was null."));
    }

    @Test
    void givenMemberToCreate_whenRegisteringEmailIncorrectly_thenThrowError() {
        CreateMemberDto createMemberDto = new CreateMemberDto(nameMapper.mapper(name),
                addressMapper.mapper(address), "557", null, licensePlateMapper.mapper(licensePlate));

        RestAssured
                .given()
                .body(createMemberDto)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/members")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", Matchers.is("Required field EMAIL was null."));
    }

    @Test
    void givenMemberToCreate_whenRegisteringLicenseNumberIncorrectly_thenThrowError() {
        LicensePlate faultyPlate = new LicensePlate(null, "null");
        CreateMemberDto createMemberDto = new CreateMemberDto(nameMapper.mapper(name),
                addressMapper.mapper(address), "557", "@number", licensePlateMapper.mapper(faultyPlate));

        RestAssured
                .given()
                .body(createMemberDto)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/members")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", Matchers.is("Required field LICENSE PLATE - NUMBER was null."));
    }

    @Test
    void givenMemberToCreate_whenRegisteringLicenseLabelIncorrectly_thenThrowError() {
        LicensePlate faultyPlate = new LicensePlate("null", null);
        CreateMemberDto createMemberDto = new CreateMemberDto(nameMapper.mapper(name),
                addressMapper.mapper(address), "557", "@number", licensePlateMapper.mapper(faultyPlate));

        RestAssured
                .given()
                .body(createMemberDto)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/members")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", Matchers.is("Required field LICENSE PLATE - COUNTRY LABEL was null."));
    }

    @Test
    void givenDivisionInDatabase_whenGettingAllDivisions_thenAllDivisionsAreReturned() {


        CreateMemberDto memberDto1 = new CreateMemberDto(
                nameMapper.mapper(new Name("First", "Last")),
                addressMapper.mapper(new Address("Sesam", "123", new PostalCode("3000", "Leuven"))),
                "02/8985847",
                "wtf@wtf.com",
                licensePlateMapper.mapper(new LicensePlate("1-TYR-963", "BE")));

        CreateMemberDto memberDto2 = new CreateMemberDto(
                nameMapper.mapper(new Name("First2", "Last2")),
                addressMapper.mapper(new Address("Sesam", "123", new PostalCode("3000", "Leuven"))),
                "02/8985847",
                "wtf@wtf.com",
                licensePlateMapper.mapper(new LicensePlate("1-TYR-963", "BE")));

        memberService.addMember(memberDto1);
        memberService.addMember(memberDto2);

        List<MemberDto> memberDtoList = RestAssured
                .given()
                .contentType(JSON)
//                .header
                .when()
                .port(port)
                .get("/members")
                .then()
                .assertThat()
                .statusCode(OK.value())
                .extract()
                .jsonPath().getList(".", MemberDto.class);

        Assertions.assertThat(memberDtoList.get(0).name()).isEqualTo(memberDto1.name());
        Assertions.assertThat(memberDtoList.get(0).address()).isEqualTo(memberDto1.address());
        Assertions.assertThat(memberDtoList.get(0).phoneNumber()).isEqualTo(memberDto1.phoneNumber());
        Assertions.assertThat(memberDtoList.get(0).email()).isEqualTo(memberDto1.email());
        Assertions.assertThat(memberDtoList.get(0).phoneNumber()).isEqualTo(memberDto1.phoneNumber());
        Assertions.assertThat(memberDtoList.get(0).licensePlate()).isEqualTo(memberDto1.licensePlate());

        Assertions.assertThat(memberDtoList.get(1).name()).isEqualTo(memberDto2.name());
        Assertions.assertThat(memberDtoList.get(1).address()).isEqualTo(memberDto2.address());
        Assertions.assertThat(memberDtoList.get(1).phoneNumber()).isEqualTo(memberDto2.phoneNumber());
        Assertions.assertThat(memberDtoList.get(1).email()).isEqualTo(memberDto2.email());
        Assertions.assertThat(memberDtoList.get(1).phoneNumber()).isEqualTo(memberDto2.phoneNumber());
        Assertions.assertThat(memberDtoList.get(1).licensePlate()).isEqualTo(memberDto2.licensePlate());

    }


}