package org.kie.kogito.integrationtests.springboot;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = KogitoSpringbootApplication.class)
class ElementAtIndexTest extends BaseRestTest {

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
