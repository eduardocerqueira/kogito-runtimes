package org.kie.kogito.addons.quarkus.knative.eventing;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.quarkus.test.junit.TestProfile;
import io.restassured.RestAssured;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.not;
import static org.kie.kogito.addons.quarkus.knative.eventing.KSinkInjectionHealthCheck.CONFIG_ALIAS;

@QuarkusIntegrationTest
@TestProfile(KSinkInjectionHealthCheckDisabledProfile.class)
class KSinkInjectionHealthCheckDisabledIT extends AbstractKSinkInjectionHealthCheckIT {

    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void kSinkInjectionHealthDisabled() {
        assertThat(config.getOptionalValue(CONFIG_ALIAS, Boolean.class)).hasValue(false);
        assertHealthChecks(not(hasItems(hasEntry("name", KSinkInjectionHealthCheck.NAME))));
    }
}
