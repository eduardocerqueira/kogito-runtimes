package org.kie.kogito.quarkus.workflows;

import org.junit.jupiter.api.Test;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.quarkus.test.junit.TestProfile;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTestResource(GrpcServerPortResource.class)
@QuarkusIntegrationTest
@TestProfile(EmitEnumProfile.class)
class EmitEnumRPCGreetIT {

    @Test
    void testStateIsUnknown() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"workflowdata\" : {\"name\" : \"Javierito\", \"language\":\"Spanish\", \"unknown\": true}}").when()
                .post("/rpc-greet")
                .then()
                .statusCode(201)
                .body("workflowdata.state", is("UNKNOWN"))
                .body("workflowdata.innerMessage.state", is("UNKNOWN"))
                .body("workflowdata.minority[0].state", is("UNKNOWN"))
                .body("workflowdata.minority[0].innerMessage.state", is("UNKNOWN"))
                .body("workflowdata.minority[1].state", is("UNKNOWN"))
                .body("workflowdata.minority[1].innerMessage.state", is("UNKNOWN"));
    }
}
