package org.kie.kogito.integrationtests.springboot;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.kie.kogito.integrationtests.springboot.utils.DataIndexWireMockSpringBootTestResource;
import org.kie.kogito.test.utils.CustomSVGMatcher;

import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.ContextConfiguration;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = KogitoSpringbootApplication.class)
@ContextConfiguration(initializers = DataIndexWireMockSpringBootTestResource.class)
public class ProcessSvgAddonTest extends BaseRestTest {

    public static String readFileContent(String file) throws URISyntaxException, IOException {
        Path path = Paths.get(Thread.currentThread().getContextClassLoader().getResource(file).toURI());
        return new String(Files.readAllBytes(path));
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
                .body(CustomSVGMatcher.isSimilarTo(readFileContent("META-INF/processSVG/approvals.svg")));

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
                .body(CustomSVGMatcher.isSimilarTo(readFileContent("META-INF/processSVG/approvals-expected.svg")));

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
                .body(CustomSVGMatcher.isSimilarTo(readFileContent("META-INF/processSVG/approvals-expected.svg")));
    }
}
