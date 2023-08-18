package org.kie.kogito.integrationtests.springboot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.http.ContentType;

/**
 * Part of build certification process. Please do not remove.
 * Smoke test of kogito end-to-end scenarios.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = KogitoSpringbootApplication.class)
public class HospitalsStatusTest extends BaseRestTest {

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