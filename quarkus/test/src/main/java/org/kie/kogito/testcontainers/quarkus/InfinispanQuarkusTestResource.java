package org.kie.kogito.testcontainers.quarkus;

import java.util.HashMap;
import java.util.Map;

import org.kie.kogito.test.resources.ConditionalQuarkusTestResource;
import org.kie.kogito.testcontainers.KogitoInfinispanContainer;

/**
 * Infinispan quarkus resource that works within the test lifecycle.
 */
public class InfinispanQuarkusTestResource extends ConditionalQuarkusTestResource<KogitoInfinispanContainer> {

    public static final String KOGITO_INFINISPAN_PROPERTY = "quarkus.infinispan-client.hosts";

    public InfinispanQuarkusTestResource() {
        super(new KogitoInfinispanContainer());
    }

    @Override
    protected Map<String, String> getProperties() {
        Map<String, String> properties = new HashMap<>();
        properties.put(KOGITO_INFINISPAN_PROPERTY, getServerUrl());
        properties.put("quarkus.infinispan-client.use-auth", "false");
        return properties;
    }

    public static class Conditional extends InfinispanQuarkusTestResource {

        public Conditional() {
            super();
            enableConditional();
        }
    }
}
