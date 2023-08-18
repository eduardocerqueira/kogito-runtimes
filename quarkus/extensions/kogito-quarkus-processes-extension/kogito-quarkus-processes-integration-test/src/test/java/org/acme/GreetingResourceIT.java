package org.acme;

import java.util.Map;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusIntegrationTest
public class GreetingResourceIT {

    @Test
    public void testHelloEndpoint() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .body(Map.of("name", "Paul"))
                .post("/hello")
                .then()
                .statusCode(200)
                .body("message", is("Hello Paul"));
    }

    @Test
    public void testVersion() {
        String version = System.getProperty("project.version");
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/hello/version")
                .then()
                .statusCode(200)
                .body("version", is(version));
    }

}
