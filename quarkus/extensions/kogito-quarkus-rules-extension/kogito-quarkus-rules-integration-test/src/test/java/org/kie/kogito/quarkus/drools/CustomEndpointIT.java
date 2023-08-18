package org.kie.kogito.quarkus.drools;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;

@QuarkusIntegrationTest
public class CustomEndpointIT {

    @Test
    public void testAdult() {
        String payload = "{ \"eventData\": [{ \"type\": \"temperature\", \"value\" : 40 }] }";
        RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post("/custom")
                .then()
                .statusCode(200)
                .body("alerts.severity", is("warning"))
                .body("alerts.message", startsWith("Event"));
    }
}
