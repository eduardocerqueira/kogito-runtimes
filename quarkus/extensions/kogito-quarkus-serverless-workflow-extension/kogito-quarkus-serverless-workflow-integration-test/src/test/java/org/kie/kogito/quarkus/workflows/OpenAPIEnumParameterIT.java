package org.kie.kogito.quarkus.workflows;

import java.util.Map;

import org.junit.jupiter.api.Test;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusIntegrationTest
@QuarkusTestResource(EnumEchoServiceMock.class)
class OpenAPIEnumParameterIT {

    @Test
    void test() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .body(Map.of())
                .post("/openapienumparameter")
                .then()
                .statusCode(201)
                .body("workflowdata.echoedMsgType", is("text"));
    }
}
