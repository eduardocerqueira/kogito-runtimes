package org.kie.kogito.integrationtests.quarkus;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.kie.kogito.integrationtests.quarkus.utils.DataIndexWiremock;
import org.kie.kogito.test.utils.CustomSVGMatcher;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

@QuarkusIntegrationTest
@QuarkusTestResource(DataIndexWiremock.class)
public class ProcessSvgAddonIT {

    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    public static String readFileContent(String file) throws URISyntaxException, IOException {
        Path path = Paths.get(Thread.currentThread().getContextClassLoader().getResource(file).toURI());
        return Files.readString(path);
    }

    @Test
    void testInvalidSvgProcessInstances() {
        given().when()
                .get("/svg/processes/{processId}", "aprocess")
                .then()
                .statusCode(404);

        given().when()
                .get("/svg/processes/{processId}/instances/{processInstanceId}", "aprocess", "id")
                .then()
                .statusCode(404);
    }

    @Test
    void testGetSvgProcessInstances() throws Exception {
        given().when()
                .get("/svg/processes/{processId}", "approvals")
                .then()
                .statusCode(200)
                .body(CustomSVGMatcher.isSimilarTo(readFileContent("processSVG/approvals-expected.svg")));

        String pId = given()
                .contentType(ContentType.JSON)
                .when()
                .post("/approvals")
                .then()
                .statusCode(201)
                .extract().body().path("id");

        given().when()
                .get("/svg/processes/{processId}/instances/{processInstanceId}", "approvals", pId)
                .then()
                .statusCode(200)
                .body(CustomSVGMatcher.isSimilarTo(readFileContent("processSVG/approvals-instance-expected.svg")));

        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/approvals/{pId}", pId)
                .then()
                .statusCode(200);

        given().when()
                .get("/svg/processes/{processId}/instances/{processInstanceId}", "approvals", pId)
                .then()
                .statusCode(200)
                .body(CustomSVGMatcher.isSimilarTo(readFileContent("processSVG/approvals-instance-expected.svg")));
    }
}
