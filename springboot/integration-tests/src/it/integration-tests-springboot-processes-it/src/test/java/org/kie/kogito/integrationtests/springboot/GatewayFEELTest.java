package org.kie.kogito.integrationtests.springboot;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.nullValue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = KogitoSpringbootApplication.class)
class GatewayFEELTest extends BaseRestTest {

    @Test
    void testGatewayFEEL() {
        given().body("{ \"va\": true, \"vb\": false }")
                .contentType(ContentType.JSON)
                .when()
                .post("/BPMN2GatewayFEEL")
                .then()
                .statusCode(201)
                .body("id", not(emptyOrNullString()))
                .body("task1", is("ok"))
                .body("task2", is("ok"))
                .body("task3", nullValue())
                .extract()
                .path("id");
        given().body("{ \"va\": false, \"vb\": true }")
                .contentType(ContentType.JSON)
                .when()
                .post("/BPMN2GatewayFEEL")
                .then()
                .statusCode(201)
                .body("id", not(emptyOrNullString()))
                .body("task1", is("ok"))
                .body("task2", nullValue())
                .body("task3", is("ok"))
                .extract()
                .path("id");
    }
}
