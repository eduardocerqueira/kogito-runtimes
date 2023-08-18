package org.kie.kogito.quarkus.workflows;

import org.junit.jupiter.api.Test;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTestResource(PerfectSquareServiceMock.class)
@QuarkusIntegrationTest
public class ErrorRestIT {

    @Test
    public void testErrorRest() {
        innerErrorRest("error");
    }

    @Test
    public void testErrorGenericRest() {
        innerErrorRest("errorGeneric");
    }

    @Test
    public void testErrorRepeatedRest() {
        innerErrorRest("errorRepeated");
    }

    private void innerErrorRest(String workflowId) {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"workflowdata\" : {\"number\" : 1}}").when()
                .post("/" + workflowId)
                .then()
                .statusCode(201)
                .body("workflowdata.numberType", is("odd"))
                .body("workflowdata.perfect", is("Error invoking publishPerfectSquare"));
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"workflowdata\" : {\"number\" : 4}}").when()
                .post("/" + workflowId)
                .then()
                .statusCode(201)
                .body("workflowdata.numberType", is("even"))
                .body("workflowdata.perfect", is(true));
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"workflowdata\" : {\"number\" : 6}}").when()
                .post("/" + workflowId)
                .then()
                .statusCode(201)
                .body("workflowdata.numberType", is("even"))
                .body("workflowdata.perfect", is(false));
    }
}
