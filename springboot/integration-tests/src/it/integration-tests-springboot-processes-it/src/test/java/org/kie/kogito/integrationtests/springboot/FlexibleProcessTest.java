package org.kie.kogito.integrationtests.springboot;

import java.util.HashMap;
import java.util.Map;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.emptyOrNullString;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = KogitoSpringbootApplication.class)
class FlexibleProcessTest extends BaseRestTest {

    @Test
    void testInstantiateProcess() {
        Map<String, String> params = new HashMap<>();
        params.put("var1", "first");
        params.put("var2", "second");

        String pid = given()
                .contentType(ContentType.JSON)
            .when()
                .body(params)
                .post("/AdHocProcess")
            .then()
                .statusCode(201)
                .header("Location", not(emptyOrNullString()))
                .body("id", not(emptyOrNullString()))
                .body("var1", equalTo("Hello first! Script"))
                .body("var2", equalTo("second Script 2"))
            .extract()
                .path("id");

        given()
                .contentType(ContentType.JSON)
            .when()
                .get("/AdHocProcess/{pid}", pid)
            .then()
                .statusCode(200);
    }
}
