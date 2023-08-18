package org.kie.kogito.integrationtests.quarkus;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;

@QuarkusIntegrationTest
public class SagaIT {

    public static final String PATH = "/saga_error";

    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void testSuccessProcess() {
        Map<String, String> payload = new HashMap<>();
        payload.put("name", "Arthur");
        payload.put("error", "HasNoError");
        given()
                .contentType(ContentType.JSON)
                .when()
                .body(payload)
                .post(PATH)
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("name", equalTo("Arthur"))
                .body("response", equalTo("Hello Arthur!"))
                .body("responseError", equalTo("Hello HasNoError!"));
    }

    @Test
    void testFailedProcess() {
        Map<String, String> payload = new HashMap<>();
        payload.put("name", "Arthur");
        payload.put("error", "error");
        given()
                .contentType(ContentType.JSON)
                .when()
                .body(payload)
                .post(PATH)
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("name", equalTo("Arthur"))
                .body("response", equalTo("Failed"))
                .body("responseError", nullValue());
    }
}
