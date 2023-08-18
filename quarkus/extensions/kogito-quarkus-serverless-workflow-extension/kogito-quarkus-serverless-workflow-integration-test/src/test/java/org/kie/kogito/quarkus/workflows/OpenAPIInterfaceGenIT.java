package org.kie.kogito.quarkus.workflows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

/**
 * Handle integration tests for OpenAPI Spec Interface generation for SW projects.
 */
@QuarkusIntegrationTest
public class OpenAPIInterfaceGenIT {

    @BeforeAll
    static void init() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void verifyOperationIdIsGeneratedByDefault() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/q/openapi?format=json")
                .then()
                .statusCode(200)
                // verifies the get path in the helloworld SW
                .body("paths.'/helloworld'.get.operationId", is("getResources_helloworld"));
    }

}
