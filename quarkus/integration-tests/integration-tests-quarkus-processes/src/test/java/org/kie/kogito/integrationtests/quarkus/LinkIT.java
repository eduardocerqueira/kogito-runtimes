package org.kie.kogito.integrationtests.quarkus;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

@QuarkusIntegrationTest
class LinkIT {

    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void testLink() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/SimpleLinkTest")
                .then()
                .statusCode(201);
    }

    @Test
    void testMultipleLink() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/MultipleThrowLinkProcess")
                .then()
                .statusCode(201);
    }

}
