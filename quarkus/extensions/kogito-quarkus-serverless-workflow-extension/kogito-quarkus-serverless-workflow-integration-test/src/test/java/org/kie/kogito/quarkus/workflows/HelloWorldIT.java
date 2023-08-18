package org.kie.kogito.quarkus.workflows;

import java.util.Collections;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.kie.kogito.serverless.workflow.SWFConstants;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusIntegrationTest
class HelloWorldIT {

    @BeforeAll
    static void init() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void testeEmptyBodyHelloWorld() {
        byte counter = 2;
        do {
            doIt(Collections.emptyMap());
        } while (counter-- > 0);
    }

    @Test
    void testStringBodyHelloWorld() {
        doIt(Collections.singletonMap(SWFConstants.DEFAULT_WORKFLOW_VAR, ""));
    }

    @Test
    void testNoBodyHelloWorld() {
        doIt(null);
    }

    @Test
    void testEmptyWorkflowDataHelloWorld() {
        doIt(Collections.singletonMap(SWFConstants.DEFAULT_WORKFLOW_VAR, Collections.emptyMap()));
    }

    private void doIt(Object body) {
        RequestSpecification request = given()
                .contentType(ContentType.JSON);
        if (body != null) {
            request = request.body(body);
        }
        request.post("/helloworld").then().statusCode(201).body("workflowdata.result", is("Hello World!"));
    }
}
