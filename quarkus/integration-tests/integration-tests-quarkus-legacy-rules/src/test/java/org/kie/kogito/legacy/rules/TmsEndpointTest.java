package org.kie.kogito.legacy.rules;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class TmsEndpointTest {
    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void testHelloEndpoint() {
        given()
                .queryParam("string", "test")
                .when().post("/test-tms")
                .then()
                .statusCode(200)
                .body(is("1")); // 1 rule fired

        given()
                .when().get("/test-tms")
                .then()
                .statusCode(200)
                .body(is("4")); // the session contains an int equal to the length of the inserted string

        given()
                .queryParam("string", "test")
                .when().delete("/test-tms")
                .then()
                .statusCode(200)
                .body(is("0")); // no rule fired

        given()
                .when().get("/test-tms")
                .then()
                .statusCode(200)
                .body(is("-1")); // the deletion of the string also causes the retraction of the logical asserted int
    }
}
