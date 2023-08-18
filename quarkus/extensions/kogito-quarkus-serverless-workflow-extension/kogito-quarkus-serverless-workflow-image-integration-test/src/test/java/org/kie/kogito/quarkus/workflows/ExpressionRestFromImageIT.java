package org.kie.kogito.quarkus.workflows;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;

@QuarkusIntegrationTest
public class ExpressionRestFromImageIT {

    @Test
    void testExpressionRest() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("pepe", "pepa")
                .body("{\"workflowdata\":{\"numbers\":[{\"x\":2, \"y\": 1},{\"x\":4, \"y\": 3}]}}").when()
                .post("/expression")
                .then()
                .statusCode(201)
                .body("workflowdata.result", is(4))
                .body("workflowdata.number", nullValue())
                .body("workflowdata.message", is("my name is kogito and in my native language dog is translated to perro and the header pepe is pepa"))
                .body("workflowdata.discardedResult", nullValue());
    }
}
