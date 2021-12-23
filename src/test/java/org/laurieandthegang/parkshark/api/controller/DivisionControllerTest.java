package org.laurieandthegang.parkshark.api.controller;

import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.laurieandthegang.parkshark.api.dto.parkinglot.CreateDivisionDto;
import org.laurieandthegang.parkshark.api.dto.parkinglot.DivisionDto;
import org.laurieandthegang.parkshark.repository.DivisionRepository;
import org.laurieandthegang.parkshark.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.List;

import static io.restassured.http.ContentType.JSON;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class DivisionControllerTest {

    @LocalServerPort
    private int port;

    private String url;
    private String response;

    @Autowired
    private DivisionRepository divisionRepository;
    @Autowired
    private DivisionService divisionService;

    @BeforeAll
    void setUp(){
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
    void givenDivisionToCreate_WhenRegisterDivisionCorrectly_thenAddDivision() {
        CreateDivisionDto createDivisionDTO = new CreateDivisionDto("name", "originalName", "director", null);

        DivisionDto divisionDto = RestAssured
                .given()
                .auth()
                .oauth2(response)
                .body(createDivisionDTO)
                .accept(JSON)
                .contentType(JSON)
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

    @Test
    void givenDivisionInDatabase_whenGettingAllDivisions_thenAllDivisionsAreReturned() {

        CreateDivisionDto divisionDto1 = new CreateDivisionDto("name1", "originalName1", "director1", null);
        CreateDivisionDto divisionDto2 = new CreateDivisionDto("name2", "originalName2", "director2", null);

        divisionService.addDivision(divisionDto1);
        divisionService.addDivision(divisionDto2);

        List<DivisionDto> divisionDtoList = RestAssured
                .given()
                .contentType(JSON)
//                .header
                .when()
                .port(port)
                .get("/divisions")
                .then()
                .assertThat()
                .statusCode(OK.value())
                .extract()
                .jsonPath().getList(".", DivisionDto.class);

        Assertions.assertThat(divisionDtoList.get(0).name()).isEqualTo(divisionDto1.name());
        Assertions.assertThat(divisionDtoList.get(0).originalName()).isEqualTo(divisionDto1.originalName());
        Assertions.assertThat(divisionDtoList.get(0).director()).isEqualTo(divisionDto1.director());
        Assertions.assertThat(divisionDtoList.get(1).name()).isEqualTo(divisionDto2.name());
        Assertions.assertThat(divisionDtoList.get(1).originalName()).isEqualTo(divisionDto2.originalName());
        Assertions.assertThat(divisionDtoList.get(1).director()).isEqualTo(divisionDto2.director());
    }

    @Test
    void givenSubdivisionInDatabase_whenGettingAllDivisions_thenDivisonsHasAParentDivisions() {

        CreateDivisionDto divisionDto1 = new CreateDivisionDto("name1", "originalName1", "director1", null);
        CreateDivisionDto divisionDto2 = new CreateDivisionDto("name2", "originalName2", "director2", 1);

        divisionService.addDivision(divisionDto1);
        divisionService.addDivision(divisionDto2);

        List<DivisionDto> divisionDtoList = RestAssured
                .given()
                .contentType(JSON)
//                .header
                .when()
                .port(port)
                .get("/divisions")
                .then()
                .assertThat()
                .statusCode(OK.value())
                .extract()
                .jsonPath().getList(".", DivisionDto.class);

        Assertions.assertThat(divisionDtoList.get(1).parentDivision().name()).isEqualTo(divisionDto1.name());
        Assertions.assertThat(divisionDtoList.get(1).parentDivision().originalName()).isEqualTo(divisionDto1.originalName());
        Assertions.assertThat(divisionDtoList.get(1).parentDivision().director()).isEqualTo(divisionDto1.director());
    }
}