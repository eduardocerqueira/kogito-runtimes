package org.kie.kogito.process.definitions;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class ProcessDefinitionsResourceTest {

    @Test
    void testAddDefinition() throws IOException {
        given()
                .contentType(ContentType.TEXT)
                .accept(ContentType.TEXT)
                .body(new String(Thread.currentThread().getContextClassLoader().getResource("helloworld.sw.json").openStream().readAllBytes())).when()
                .post("/helloworld/definition")
                .then()
                .statusCode(200);

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post("/helloworld")
                .then()
                .statusCode(201)
                .body("workflowdata.result", containsString("Hello World!"));
    }
}
