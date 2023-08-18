package org.kie.kogito.integrationtests.quarkus;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusIntegrationTest
class BasicAddIT {

    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void testWholeModel() {
        given().body("{ \"a\": \"v1\", \"b\": \"v2\" }")
                .contentType(ContentType.JSON)
                .when()
                .post("/basicAdd")
                .then()
                .statusCode(200)
                .body("decision", is("v1v2"));
    }

    @Test
    void testWholeModel_dmnresult() {
        given().body("{ \"a\": \"v1\", \"b\": \"v2\" }")
                .contentType(ContentType.JSON)
                .when()
                .post("/basicAdd/dmnresult")
                .then()
                .statusCode(200)
                .body("dmnContext.decision", is("v1v2"));
    }

    @Test
    void testDs1() {
        given().body("{ \"a\": \"v1\", \"b\": \"v2\" }")
                .contentType(ContentType.JSON)
                .when()
                .post("/basicAdd/ds1")
                .then()
                .statusCode(200)
                .body(is("\"v1v2\"")); // a JSON string literal: "v1v2"
    }

    @Test
    void testDs1_dmnresult() {
        given().body("{ \"a\": \"v1\", \"b\": \"v2\" }")
                .contentType(ContentType.JSON)
                .when()
                .post("/basicAdd/ds1/dmnresult")
                .then()
                .statusCode(200)
                .body("dmnContext.decision", is("v1v2"));
    }
}
