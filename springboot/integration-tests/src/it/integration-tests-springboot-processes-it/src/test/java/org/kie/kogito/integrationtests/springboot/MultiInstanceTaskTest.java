package org.kie.kogito.integrationtests.springboot;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.context.SpringBootTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = KogitoSpringbootApplication.class)
class MultiInstanceTaskTest extends BaseRestTest {

    @Test
    void testStartProcess() {
        String processId = given()
                .contentType(ContentType.JSON)
                .when()
                .body(Collections.singletonMap("inputList", Arrays.asList("user1", "user2")))
                .post("/multiinstancetaskprocess")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        List<String> tasks = given()
                .contentType(ContentType.JSON)
                .queryParam("user", "admin")
                .queryParam("group", "managers")
                .pathParam("processId", processId)
                .when()
                .get("/multiinstancetaskprocess/{processId}/tasks")
                .then()
                .statusCode(200)
                .body("$.size()", equalTo(2))
                .extract()
                .jsonPath().getList("id");

        assertThat(tasks).hasSize(2);

        given()
                .contentType(ContentType.JSON)
                .when()
                .body(Collections.singletonMap("output", "user3"))
                .post("/multiinstancetaskprocess/{id}/MultiInstanceTask/{tId}", processId, tasks.get(0))
                .then()
                .statusCode(200);

        given()
                .contentType(ContentType.JSON)
                .when()
                .body(Collections.singletonMap("output", "user4"))
                .post("/multiinstancetaskprocess/{id}/MultiInstanceTask/{tId}", processId, tasks.get(1))
                .then()
                .statusCode(200)
                .body("inputList", hasItems("user1", "user2"))
                .body("outputList", hasItems("user3", "user4"));

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/multiinstancetaskprocess/{id}", processId)
                .then()
                .statusCode(404);
    }
}
