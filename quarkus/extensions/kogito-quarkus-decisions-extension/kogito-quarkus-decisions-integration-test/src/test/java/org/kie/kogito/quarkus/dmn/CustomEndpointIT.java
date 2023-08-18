package org.kie.kogito.quarkus.dmn;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

@QuarkusIntegrationTest
public class CustomEndpointIT {

    @Test
    public void testHelloEndpoint() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(DMNIT.ADULT_PAYLOAD)
                .post("/custom")
                .then()
                .statusCode(200)
                .body("data.d.Hello", Matchers.is("Certidão Hello Luca"));
    }

}
