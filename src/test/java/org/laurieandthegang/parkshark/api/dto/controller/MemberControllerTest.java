package org.laurieandthegang.parkshark.api.dto.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.laurieandthegang.parkshark.api.dto.CreateMemberDto;
import org.laurieandthegang.parkshark.api.dto.MemberDto;
import org.laurieandthegang.parkshark.domain.people.Address;
import org.laurieandthegang.parkshark.domain.people.LicensePlate;
import org.laurieandthegang.parkshark.domain.people.Name;
import org.laurieandthegang.parkshark.domain.people.PostalCode;
import org.laurieandthegang.parkshark.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static io.restassured.http.ContentType.JSON;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class MemberControllerTest {

    @Value("${server.port}")
    private int port;

    @Autowired
    private MemberRepository memberRepository;


    @Test
    void givenMemberToCreate_whenRegisteringMemberCorrectly_thenAddMember() {
        Name name = new Name("tim", "tom");
        PostalCode postalCode = new PostalCode("toktg", "oghf");
        Address address = new Address("timtom", "tomtim", postalCode);
        LicensePlate licensePlate = new LicensePlate("shfdug", "België.");
        CreateMemberDto createMemberDto = new CreateMemberDto(name, address, "557", "@number", licensePlate);

        MemberDto memberDto = RestAssured
                .given()
                .body(createMemberDto)
                .accept(JSON)
                .contentType(JSON)
//                .header()
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
    void givenMemberToCreate_whenRegisteringMemberIncorrectly_thenThrowError() {
        CreateMemberDto createMemberDto = new CreateMemberDto(null, null, null, null, null);

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
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

    }
}