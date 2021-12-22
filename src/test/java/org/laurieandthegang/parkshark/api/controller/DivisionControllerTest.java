package org.laurieandthegang.parkshark.api.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.laurieandthegang.parkshark.api.dto.CreateDivisionDto;
import org.laurieandthegang.parkshark.api.dto.DivisionDto;
import org.laurieandthegang.parkshark.repository.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class DivisionControllerTest {

    @Value("${server.port}")
    private int port;

    @Autowired
    private DivisionRepository divisionRepository;

    @Test
    void givenDivisionToCreate_WhenRegisterDivisionCorrectly_thenAddDivision() {
        CreateDivisionDto createDivisionDTO = new CreateDivisionDto("name", "originalName", "director");

        DivisionDto divisionDto = RestAssured
                .given()
                .body(createDivisionDTO)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
//                .header()
                .when()
                .port(port)
                .post("/divisions")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(DivisionDto.class);

        Assertions.assertThat(divisionDto.name()).isEqualTo(createDivisionDTO.name());
        Assertions.assertThat(divisionDto.originalName()).isEqualTo(createDivisionDTO.originalName());
        Assertions.assertThat(divisionDto.director()).isEqualTo(createDivisionDTO.director());
    }
}