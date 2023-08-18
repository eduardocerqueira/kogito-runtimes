package org.kie.kogito.integrationtests.quarkus;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.emptyOrNullString;

@QuarkusIntegrationTest
class FlexibleProcessIT {

    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void testInstantiateProcess() {
        Map<String, String> params = new HashMap<>();
        params.put("var1", "first");
        params.put("var2", "second");

        String pid = given()
                .contentType(ContentType.JSON)
                .when()
                .body(params)
                .post("/AdHocProcess")
                .then()
                .statusCode(201)
                .header("Location", not(emptyOrNullString()))
                .body("id", not(emptyOrNullString()))
                .body("var1", equalTo("Hello first! Script"))
                .body("var2", equalTo("second Script 2"))
                .extract()
                .path("id");

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/AdHocProcess/{pid}", pid)
                .then()
                .statusCode(200);
    }

    @Test
    void testProcessException() {
        Map<String, String> params = new HashMap<>();
        params.put("var1", "exception");
        params.put("var2", "second");

        given()
                .contentType(ContentType.JSON)
                .when()
                .body(params)
                .post("/AdHocProcess")
                .then()
                .statusCode(500);
    }
}
