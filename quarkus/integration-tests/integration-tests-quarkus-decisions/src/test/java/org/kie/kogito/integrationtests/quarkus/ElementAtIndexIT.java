package org.kie.kogito.integrationtests.quarkus;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

@QuarkusIntegrationTest
class ElementAtIndexIT {

    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void testHeaderEmpty() {
        given().body(" { \"a list\" : [\"a\", \"b\", \"c\"], \"an index\" : 1 }")
                .contentType(ContentType.JSON)
                .when()
                .post("/ElementAtIndex")
                .then()
                .statusCode(200)
                .header("X-Kogito-decision-messages", nullValue())
                .body("'element at index'", is("a"));
    }

    @Test
    void testHeaderPopulated() {
        given().body(" { \"a list\" : [\"a\", \"b\", \"c\"], \"an index\" : 47 }")
                .contentType(ContentType.JSON)
                .when()
                .post("/ElementAtIndex")
                .then()
                .statusCode(200)
                .header("X-Kogito-decision-messages", notNullValue()) // a warning
                .body("'element at index'", nullValue());
    }

    @Test
    void testGET() {
        given().accept(ContentType.XML)
                .when()
                .get("/ElementAtIndex")
                .then()
                .statusCode(200)
                .body("definitions.decision[0].children().size()", is(4))
                .body("definitions.children().findAll { node -> node.name() == 'literalExpression' }.size()", is(0));
    }

}
