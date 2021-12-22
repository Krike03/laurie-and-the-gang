package org.laurieandthegang.parkshark.api.controller;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.laurieandthegang.parkshark.api.dto.CreateMemberDto;
import org.laurieandthegang.parkshark.api.dto.MemberDto;
import org.laurieandthegang.parkshark.domain.people.Address;
import org.laurieandthegang.parkshark.domain.people.LicensePlate;
import org.laurieandthegang.parkshark.domain.people.Name;
import org.laurieandthegang.parkshark.domain.people.PostalCode;
import org.laurieandthegang.parkshark.exception.RequiredFieldIsNullException;
import org.laurieandthegang.parkshark.repository.MemberRepository;
import org.mockito.internal.matchers.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class MemberControllerTest {

    @Value("${server.port}")
    private int port;

    @Autowired
    private MemberRepository memberRepository;

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
    }

    @Test
    void givenMemberToCreate_whenRegisteringMemberCorrectly_thenAddMember() {
        CreateMemberDto createMemberDto = new CreateMemberDto(name, address, "557", "@number", licensePlate);

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
        CreateMemberDto createMemberDto = new CreateMemberDto(name, faultyAddress, "557", "@number", licensePlate);

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
        CreateMemberDto createMemberDto = new CreateMemberDto(name, faultyAddress, "557", "@number", licensePlate);
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

        CreateMemberDto createMemberDto = new CreateMemberDto(name, faultyAddress, "557", "@number", licensePlate);

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
                .body("message", Matchers.is("Required field ADDRESS - POSTAL CODE - NUMERAL was null."));
    }

    @Test
    void givenMemberToCreate_whenRegisteringCityLabelIncorrectly_thenThrowError() {
        PostalCode faultyPostal = new PostalCode("null", null);
        Address faultyAddress = new Address("null xd", "Gent", faultyPostal);

        CreateMemberDto createMemberDto = new CreateMemberDto(name, faultyAddress, "557", "@number", licensePlate);
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
        CreateMemberDto createMemberDto = new CreateMemberDto(name, address, null, "@number", licensePlate);

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
        CreateMemberDto createMemberDto = new CreateMemberDto(name, address, "557", null, licensePlate);

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
        CreateMemberDto createMemberDto = new CreateMemberDto(name, address, "557", "@number", faultyPlate);

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
        CreateMemberDto createMemberDto = new CreateMemberDto(name, address, "557", "@number", faultyPlate);

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


}