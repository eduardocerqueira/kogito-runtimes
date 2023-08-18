package org.kie.kogito.quarkus.workflows;

import java.util.Collections;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusIntegrationTest
class KeepActiveIT {

    @BeforeAll
    static void init() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void testKeepActive() {
        Map<String, Object> body = Collections.singletonMap("keepActive", true);
        String id = given()
                .contentType(ContentType.JSON)
                .when()
                .body(Collections.singletonMap("workflowdata", body))
                .post("/keepActive")
                .then()
                .statusCode(201)
                .extract().path("id");
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get("/keepActive/{id}", id)
                .then().statusCode(200).body("workflowdata.message", is("this will never end"));

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .delete("/keepActive/{id}", id);

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get("/keepActive/{id}", id)
                .then().statusCode(404);

    }

    @Test
    void testEndIt() {
        Map<String, Object> body = Collections.singletonMap("keepActive", false);
        String id = given()
                .contentType(ContentType.JSON)
                .when()
                .body(Collections.singletonMap("workflowdata", body))
                .post("/keepActive")
                .then()
                .statusCode(201)
                .extract().path("id");
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get("/keepActive/{id}", id)
                .then().statusCode(404);
    }

}
