package org.kie.kogito.quarkus.workflows;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasLength;

@QuarkusIntegrationTest
class ParallelStateIT {

    @Test
    void testAllParallelRest() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"workflowdata\":{\"numCompleted\": 3}}").when()
                .post("/parallel")
                .then()
                .statusCode(201)
                .body("workflowdata.result", hasLength(3));
    }

    @Test
    void testPartialParallelRest() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"workflowdata\":{\"numCompleted\": 2}}").when()
                .post("/parallel")
                .then()
                .statusCode(201)
                .body("workflowdata.result", hasLength(2));
    }
}
