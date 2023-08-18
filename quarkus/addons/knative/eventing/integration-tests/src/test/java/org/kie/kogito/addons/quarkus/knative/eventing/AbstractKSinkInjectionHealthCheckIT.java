package org.kie.kogito.addons.quarkus.knative.eventing;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;

import static io.restassured.RestAssured.given;

abstract class AbstractKSinkInjectionHealthCheckIT {

    Config config;

    @BeforeEach
    void setUp() {
        config = ConfigProvider.getConfig();
    }

    protected void assertHealthChecks(Matcher<?> matcher) {
        given()
                .when()
                .get("/q/health/live")
                .then()
                .body("checks", matcher);
    }
}
