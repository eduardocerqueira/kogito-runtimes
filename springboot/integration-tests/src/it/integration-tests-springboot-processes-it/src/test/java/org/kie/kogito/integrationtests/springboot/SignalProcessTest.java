package org.kie.kogito.integrationtests.springboot;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.nullValue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = KogitoSpringbootApplication.class)
class SignalProcessTest extends BaseRestTest {

    @Test
    void testProcessSignals() {
        String pid = given()
                .contentType(ContentType.JSON)
            .when()
                .post("/greetings")
            .then()
                .statusCode(201)
                .body("id", not(emptyOrNullString()))
                .body("test", nullValue())
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
