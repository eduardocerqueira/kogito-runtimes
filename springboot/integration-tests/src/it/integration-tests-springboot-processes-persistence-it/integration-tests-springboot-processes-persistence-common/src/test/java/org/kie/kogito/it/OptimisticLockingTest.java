package org.kie.kogito.it;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.emptyOrNullString;

public abstract class OptimisticLockingTest extends PersistenceTest {

    @Test
    void testParallelPersistence() {
        final String pid = given().contentType(ContentType.JSON)
                .when()
                .post("/parallel")
                .then()
                .statusCode(201)
                .header("Location", not(emptyOrNullString()))
                .body("id", not(emptyOrNullString()))
                .extract()
                .path("id");

        await().atMost(TIMEOUT)
                .untilAsserted(() -> given().contentType(ContentType.JSON)
                        .when()
                        .get("/parallel/{id}", pid)
                        .then()
                        .statusCode(404));
    }
}
