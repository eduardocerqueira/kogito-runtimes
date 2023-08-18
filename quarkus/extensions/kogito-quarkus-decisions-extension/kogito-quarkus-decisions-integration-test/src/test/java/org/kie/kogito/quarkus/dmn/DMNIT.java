package org.kie.kogito.quarkus.dmn;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.aMapWithSize;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

@QuarkusIntegrationTest
public class DMNIT {

    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void testOASdmnDefinitions() {
        RestAssured.given()
                .get("/dmnDefinitions.json")
                .then()
                .statusCode(200)
                .body("definitions", aMapWithSize(greaterThan(0)));
    }

    static final String ADULT_PAYLOAD = "{\n" +
            "  \"p\": {\n" +
            "    \"addresses\": [\n" +
            "      {\n" +
            "        \"streetName\": \"Street Name\",\n" +
            "        \"streetNumber\": 1\n" +
            "      },\n" +
            "      {\n" +
            "        \"streetName\": \"Another street name\",\n" +
            "        \"streetNumber\": 2\n" +
            "      }\n" +
            "    ],\n" +
            "    \"name\": \"Luca\"\n" +
            "  }\n" +
            "}";

    @Test
    public void testAdult() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(ADULT_PAYLOAD)
                .post("/dmnModel")
                .then()
                .statusCode(200)
                .body("d.Hello", is("Certidão Hello Luca"));
    }

    @Test
    public void testAdult_dmnResult() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(ADULT_PAYLOAD)
                .post("/dmnModel/dmnresult")
                .then()
                .statusCode(200)
                .body("dmnContext.d.Hello", is("Certidão Hello Luca"));
    }

    @Test
    public void allTypes() {

        String payload = "{\n" +
                "  \"InputBoolean\": true,\n" +
                "  \"InputDTDuration\": \"P1D\",\n" +
                "  \"InputDate\": \"2020-04-17\",\n" +
                "  \"InputDateAndTime\": \"2020-04-17T10:03:51.615Z\",\n" +
                "  \"InputNumber\": 0,\n" +
                "  \"InputString\": \"string\",\n" +
                "  \"InputTime\": \"13:45\",\n" +
                "  \"InputYMDuration\": \"P1M\"\n" +
                "}";

        RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .post("/OneOfEachType")
                .then()
                .statusCode(200);
    }
}
