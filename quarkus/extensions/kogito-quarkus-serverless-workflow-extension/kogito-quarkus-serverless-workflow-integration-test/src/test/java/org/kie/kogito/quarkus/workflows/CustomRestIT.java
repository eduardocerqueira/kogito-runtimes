package org.kie.kogito.quarkus.workflows;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusIntegrationTest
class CustomRestIT {

    @Test
    void testCustomType() {
        testIt("customType");
    }

    @Test
    void testCustomFunction() {
        testIt("customFunction");
    }

    private void testIt(String path) {
        given()
                .contentType(ContentType.JSON)
                .when()
                .body(Collections.emptyMap())
                .post(path)
                .then()
                .statusCode(201)
                .body("workflowdata.name", is("John"));
    }
}
