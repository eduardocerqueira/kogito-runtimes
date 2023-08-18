package org.kie.kogito.quarkus.workflows;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusIntegrationTest
class PythonFlowIT {

    @Test
    void testPythonScript() {
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body("{\"x\" : 4}").post("/Duplicate")
                .then().statusCode(201).body("workflowdata.result", is(8));
    }

    @Test
    void testPythonService() {
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body("{\"x\" : 5}").post("/Factorial")
                .then().statusCode(201).body("workflowdata.result", is(120));
    }
}
