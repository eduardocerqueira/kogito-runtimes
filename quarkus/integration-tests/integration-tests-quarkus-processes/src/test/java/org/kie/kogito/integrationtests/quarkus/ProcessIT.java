package org.kie.kogito.integrationtests.quarkus;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

import org.acme.travels.Traveller;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

@QuarkusIntegrationTest
public class ProcessIT {

    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void testProcessSchema() throws IOException {
        testProcessSchema("approvals", "test_approvals.json");
        testProcessSchema("cinema", "test_cinema.json");
        testProcessSchema("greetings", "test_greetings.json");
    }

    @Test
    @SuppressWarnings("unchecked")
    void testStartProcess() {
        Traveller traveller = new Traveller("pepe", "rubiales", "pepe.rubiales@gmail.com", "Spanish");

        String processId = given()
                .contentType(ContentType.JSON)
                .when()
                .body(Collections.singletonMap("traveller", traveller))
                .post("/approvals")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/approvals/{id}", processId)
                .then()
                .statusCode(200)
                .body("id", equalTo(processId))
                .body("traveller.firstName", equalTo(traveller.getFirstName()))
                .body("traveller.lastName", equalTo(traveller.getLastName()))
                .body("traveller.email", equalTo(traveller.getEmail()))
                .body("traveller.nationality", equalTo(traveller.getNationality()));
    }

    private void testProcessSchema(String processId, String schemaFileName) throws IOException {
        // Quarkus returns URI with "quarkus://" scheme when running via CLI and this is not compatible with
        // matchesJsonSchemaInClasspath, while matchesJsonSchema directly accepts InputStream
        try (InputStream jsonSchema = Thread.currentThread().getContextClassLoader().getResourceAsStream(
                "testJsonSchema/" + schemaFileName)) {
            assertThat(jsonSchema).isNotNull();

            given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get("/" + processId + "/schema")
                    .then()
                    .statusCode(200)
                    .body(matchesJsonSchema(jsonSchema));
        }

    }
}
