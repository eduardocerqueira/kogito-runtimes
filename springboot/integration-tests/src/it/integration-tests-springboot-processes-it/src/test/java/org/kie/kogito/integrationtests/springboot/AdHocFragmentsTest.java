package org.kie.kogito.integrationtests.springboot;

import java.util.HashMap;
import java.util.Map;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.emptyOrNullString;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = KogitoSpringbootApplication.class)
class AdHocFragmentsTest extends BaseRestTest {

    @Test
    void testUserTaskProcess() {
        Map<String, String> params = new HashMap<>();
        params.put("var1", "Kermit");

        String id = given()
                .contentType(ContentType.JSON)
            .when()
                .body(params)
                .post("/AdHocFragments")
            .then()
                .statusCode(201)
                .header("Location", not(emptyOrNullString()))
            .extract()
                .path("id");

        String taskId = extractID(given()
                .contentType(ContentType.JSON)
            .when()
                .post("/AdHocFragments/{pid}/AdHocTask1", id)
            .then()
                .statusCode(201)
                .header("Location", not(emptyOrNullString()))
            .extract()
                .header("Location"));

        params = new HashMap<>();
        params.put("newVar1", "Gonzo");
        given()
                .contentType(ContentType.JSON)
                .when()
                .body(params)
                .post("/AdHocFragments/{id}/AdHocTask1/{taskId}", id, taskId)
                .then()
                .statusCode(200)
                .body("var1", equalTo("Gonzo"));
    }

    @Test
    void testServiceTaskProcess() {
        Map<String, String> params = new HashMap<>();
        params.put("var1", "Kermit");

        String pid = given()
                .contentType(ContentType.JSON)
            .when()
                .body(params)
                .post("/AdHocFragments")
            .then()
                .statusCode(201)
            .extract()
                .path("id");

        given()
                .contentType(ContentType.JSON)
            .when()
                .post("/AdHocFragments/{pid}/Service_Task", pid)
            .then()
                .statusCode(200)
                .body("var1", equalTo("Hello Kermit 5!"));
    }

    @Test
    void testNonAdHocUserTaskProcess() {
        Map<String, String> params = new HashMap<>();
        params.put("var1", "Kermit");

        String pid = given()
                .contentType(ContentType.JSON)
            .when()
                .body(params)
                .post("/AdHocFragments")
            .then()
                .statusCode(201)
            .extract()
                .path("id");

        given()
                .contentType(ContentType.JSON)
            .when()
                .post("/AdHocFragments/{pid}/Task", pid)
            .then()
                .statusCode(404);
    }
}
