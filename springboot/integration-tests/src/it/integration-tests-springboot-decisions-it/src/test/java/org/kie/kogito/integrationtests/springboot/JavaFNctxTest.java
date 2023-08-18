package org.kie.kogito.integrationtests.springboot;

import java.math.BigDecimal;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.config.JsonPathConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;
import static io.restassured.config.JsonConfig.jsonConfig;
import static org.hamcrest.Matchers.closeTo;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = KogitoSpringbootApplication.class)
public class JavaFNctxTest extends BaseRestTest {

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
