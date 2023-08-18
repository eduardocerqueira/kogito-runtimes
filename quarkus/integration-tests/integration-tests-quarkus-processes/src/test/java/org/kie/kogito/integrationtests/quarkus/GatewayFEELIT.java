package org.kie.kogito.integrationtests.quarkus;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.nullValue;

@QuarkusIntegrationTest
class GatewayFEELIT {

    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

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
