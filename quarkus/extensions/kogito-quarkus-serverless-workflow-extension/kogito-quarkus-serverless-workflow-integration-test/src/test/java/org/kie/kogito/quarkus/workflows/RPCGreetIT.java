package org.kie.kogito.quarkus.workflows;

import org.junit.jupiter.api.Test;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTestResource(GrpcServerPortResource.class)
@QuarkusIntegrationTest
class RPCGreetIT {

    private static final String FLOW_ID = "rpc-greet";

    @Test
    void testEnglish() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"name\" : \"John\", \"language\":\"English\"}").when()
                .post(FLOW_ID)
                .then()
                .statusCode(201)
                .body("workflowdata.message", is("Hello from gRPC service John"))
                .body("workflowdata.state", is("SUCCESS"))
                .body("workflowdata.innerMessage.number", is(23))
                .body("workflowdata.minority", hasSize(2))
                .body("workflowdata.minority[0].message", is("marquitos"))
                .body("workflowdata.minority[1].message", is("Boungiorno Marco"));

    }

    @Test
    void testSpanish() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"workflowdata\" : {\"name\" : \"Javierito\", \"language\":\"Spanish\", \"unknown\": true}}").when()
                .post(FLOW_ID)
                .then()
                .statusCode(201)
                .body("workflowdata.message", is("Saludos desde gRPC service Javierito"))
                .body("workflowdata.state", nullValue());
    }

    @Test
    void testDefaultLanguage() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"workflowdata\" : {\"name\" : \"John\"}}").when()
                .post(FLOW_ID)
                .then()
                .statusCode(201)
                .body("workflowdata.message", containsString("Hello"));
    }

    @Test
    void testUnsupportedLanguage() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"workflowdata\" : {\"name\" : \"Jan\", \"language\":\"Czech\"}}").when()
                .post(FLOW_ID)
                .then()
                .statusCode(201)
                .body("workflowdata.message", containsString("Hello"));
    }
}
