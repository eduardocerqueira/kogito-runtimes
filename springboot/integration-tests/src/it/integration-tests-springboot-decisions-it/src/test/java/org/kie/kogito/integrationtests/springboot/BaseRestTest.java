package org.kie.kogito.integrationtests.springboot;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.web.server.LocalServerPort;

public abstract class BaseRestTest {

    @LocalServerPort
    int randomServerPort;

    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    static String extractID(String location) {
        return location.substring(location.lastIndexOf("/") + 1);
    }

    @BeforeEach
    void setPort() {
        RestAssured.port = randomServerPort;
    }

}