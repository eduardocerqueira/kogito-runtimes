package org.kie.kogito.integrationtests.quarkus;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;

@QuarkusIntegrationTest
public class DecisionTableIT {

    private static final String JSON_PAYLOAD =
            "{\n" +
                    "  \"maxAmount\":5000,\n" +
                    "  \"loanApplications\":[\n" +
                    "    {\n" +
                    "      \"id\":\"ABC10001\",\n" +
                    "      \"amount\":2000,\n" +
                    "      \"deposit\":100,\n" +
                    "      \"applicant\":{\n" +
                    "        \"age\":45,\n" +
                    "        \"name\":\"John\"\n" +
                    "      }\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\":\"ABC10002\",\n" +
                    "      \"amount\":5000,\n" +
                    "      \"deposit\":100,\n" +
                    "      \"applicant\":{\n" +
                    "        \"age\":25,\n" +
                    "        \"name\":\"Paul\"\n" +
                    "      }\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\":\"ABC10015\",\n" +
                    "      \"amount\":1000,\n" +
                    "      \"deposit\":100,\n" +
                    "      \"applicant\":{\n" +
                    "        \"age\":12,\n" +
                    "        \"name\":\"George\"\n" +
                    "      }\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";

    @Test
    public void testApproved() {
        given()
                .body(JSON_PAYLOAD)
                .contentType(ContentType.JSON)
                .when()
                .post("/find-approved")
                .then()
                .statusCode(200)
                .body("id", hasItem("ABC10001"));
    }

    @Test
    public void testNotApproved() {
        given()
                .body(JSON_PAYLOAD)
                .contentType(ContentType.JSON)
                .when()
                .post("/find-not-approved-id-and-amount")
                .then()
                .statusCode(200)
                .body("$id", hasItems("ABC10002", "ABC10015"));
    }
}
