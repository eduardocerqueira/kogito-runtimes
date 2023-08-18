package org.kie.kogito.quarkus.drools;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;

@QuarkusIntegrationTest
public class DroolsIT {

    @Test
    public void testAdult() {
        String payload = "{ \"eventData\": [{ \"type\": \"temperature\", \"value\" : 40 }] }";
        RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post("/warnings/first")
                .then()
                .statusCode(200)
                .body("severity", is("warning"))
                .body("message", startsWith("Event"));
    }
}
