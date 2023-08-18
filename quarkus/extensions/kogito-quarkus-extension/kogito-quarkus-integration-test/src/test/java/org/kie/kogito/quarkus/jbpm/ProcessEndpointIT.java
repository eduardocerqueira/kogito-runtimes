package org.kie.kogito.quarkus.jbpm;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;

@QuarkusTest
public class ProcessEndpointIT {

    @Test
    public void testProcessRestEndpoint() {

        given()
                .body("{}")
                .contentType(ContentType.JSON)
                .when()
                .post("/tests")
                .then()
                .statusCode(201)
                .body("id", not(emptyOrNullString()))
                .header("Location", not(emptyOrNullString()));
    }
}
