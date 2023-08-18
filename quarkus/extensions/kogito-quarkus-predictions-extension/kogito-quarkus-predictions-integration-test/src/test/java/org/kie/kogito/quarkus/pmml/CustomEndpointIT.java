package org.kie.kogito.quarkus.pmml;

import java.util.Map;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusIntegrationTest
public class CustomEndpointIT {

    @Test
    public void testHelloEndpoint() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .body(Map.of(
                        "fld1", 3.0,
                        "fld2", 2.0,
                        "fld3", "y"))
                .post("/custom")
                .then()
                .statusCode(200)
                .body("data.fld4", is(52.5f));
    }

}
