package org.kie.kogito.addons.quarkus.knative.eventing;

import java.util.Map;

import io.quarkus.test.junit.QuarkusTestProfile;

public class KSinkInjectionHealthCheckDisabledProfile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
        return Map.of(KSinkInjectionHealthCheck.CONFIG_ALIAS, "false");
    }
}
