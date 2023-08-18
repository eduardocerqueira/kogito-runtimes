package org.kie.kogito.integrationtests.quarkus;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.config.JsonPathConfig;

import static io.restassured.RestAssured.given;
import static io.restassured.config.JsonConfig.jsonConfig;
import static org.hamcrest.Matchers.closeTo;

@QuarkusIntegrationTest
class JavaFNctxIT {

    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void testJavaFNctx() {
        given().config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL)))
                .body(" { \"Input\" : 3.14 }")
                .contentType(ContentType.JSON)
                .when()
                .post("/javaFNctx")
                .then()
                .statusCode(200)
                .body("D1", closeTo(new BigDecimal(-1), new BigDecimal(0.1))) // the scope of this test is verify Math.cos() was invoked correctly on the JDK
                .body("D2", closeTo(new BigDecimal(-1), new BigDecimal(0.1)));
    }

}
