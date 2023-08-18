package org.kie.kogito.integrationtests.quarkus;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * Part of build certification process. Please do not remove.
 * Smoke test of kogito end-to-end scenarios.
 */
@QuarkusIntegrationTest
public class HospitalsStatusIT {

    @Test
    public void testHospitalStatus() {

        final HospitalStatusTestData data = new HospitalStatusTestData();
        data.setHospital("National");

        given()
                .body(data)
                .contentType(ContentType.JSON)
                .when()
                .post("/HospitalStatus")
                .then()
                .statusCode(200)
                .body("'Current Status'", equalTo("red"));
    }
}
