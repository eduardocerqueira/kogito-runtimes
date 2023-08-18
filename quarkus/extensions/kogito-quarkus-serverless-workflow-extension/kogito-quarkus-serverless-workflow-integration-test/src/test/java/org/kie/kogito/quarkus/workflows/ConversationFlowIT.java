package org.kie.kogito.quarkus.workflows;

import java.util.Collections;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTestResource(OperationsMockService.class)
@QuarkusIntegrationTest
class ConversationFlowIT {

    @BeforeAll
    static void init() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void sanityVerification() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .body(
                        Collections
                                .singletonMap(
                                        "workflowdata",
                                        Collections.singletonMap("fahrenheit", "100")))
                .post("/fahrenheit_to_celsius")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("workflowdata.fahrenheit", is("100"))
                .body("workflowdata.celsius", is(37.808f)); //values from mock server
    }
}
