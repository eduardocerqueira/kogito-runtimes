package org.kie.kogito.integrationtests.springboot;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = KogitoSpringbootApplication.class)
class BasicAddTest extends BaseRestTest {

    @Test
    void testWholeModel() {
        given().body("{ \"a\": \"v1\", \"b\": \"v2\" }")
               .contentType(ContentType.JSON)
           .when()
               .post("/basicAdd")
           .then()
               .statusCode(200)
               .body("decision", is("v1v2"));
    }
    
    @Test
    void testWholeModel_dmnresult() {
        given().body("{ \"a\": \"v1\", \"b\": \"v2\" }")
               .contentType(ContentType.JSON)
           .when()
               .post("/basicAdd/dmnresult")
           .then()
               .statusCode(200)
               .body("dmnContext.decision", is("v1v2"));
    }
    
    @Test
    void testDs1() {
        given().body("{ \"a\": \"v1\", \"b\": \"v2\" }")
               .contentType(ContentType.JSON)
           .when()
               .post("/basicAdd/ds1")
           .then()
               .statusCode(200)
               .body(is("\"v1v2\"")); // a JSON string literal: "v1v2"
    }
    
    @Test
    void testDs1_dmnresult() {
        given().body("{ \"a\": \"v1\", \"b\": \"v2\" }")
               .contentType(ContentType.JSON)
           .when()
               .post("/basicAdd/ds1/dmnresult")
           .then()
               .statusCode(200)
               .body("dmnContext.decision", is("v1v2"));
    }

}
