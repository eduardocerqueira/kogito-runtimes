package org.acme.travels;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.emptyOrNullString;

@QuarkusTest
public class SignalProcessTest {

    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void testProcessSignals() {
        String pid = given()
                .contentType(ContentType.JSON)
            .when()
                .post("/greetings")
            .then()
                .statusCode(201)
                .body("id", not(emptyOrNullString()))
                .body("test", emptyOrNullString())
            .extract()
                .path("id");

        given()
                .contentType(ContentType.JSON)
            .when()
                .body("testvalue")
                .post("/greetings/{pid}/signalwithdata", pid)
            .then()
                .statusCode(200)
                .body("id", not(emptyOrNullString()))
                .body("test", is("testvalue"));

        given()
                .contentType(ContentType.JSON)
            .when()
                .get("/greetings/{pid}", pid)
            .then()
                .statusCode(200)
                .body("test", is("testvalue"));

        given()
                .contentType(ContentType.JSON)
            .when()
                .post("/greetings/{pid}/signalwithoutdata", pid)
            .then()
                .statusCode(200);

        given()
                .contentType(ContentType.JSON)
            .when()
                .get("/greetings/{pid}", pid)
            .then()
                .statusCode(404);
    }
}
