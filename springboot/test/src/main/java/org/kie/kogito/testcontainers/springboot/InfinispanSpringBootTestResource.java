package org.kie.kogito.testcontainers.springboot;

import java.util.HashMap;
import java.util.Map;

import org.kie.kogito.test.resources.ConditionalSpringBootTestResource;
import org.kie.kogito.testcontainers.KogitoInfinispanContainer;

/**
 * Infinispan spring boot resource that works within the test lifecycle.
 *
 */
public class InfinispanSpringBootTestResource extends ConditionalSpringBootTestResource<KogitoInfinispanContainer> {

    public static final String KOGITO_INFINISPAN_PROPERTY = "infinispan.remote.server-list";

    public InfinispanSpringBootTestResource() {
        super(new KogitoInfinispanContainer());
    }

    @Override
    protected Map<String, String> getProperties() {
        Map<String, String> properties = new HashMap<>();
        properties.put(KOGITO_INFINISPAN_PROPERTY, "localhost:" + getTestResource().getMappedPort());
        properties.put("infinispan.remote.use-auth", "false");
        return properties;
    }

    public static class Conditional extends InfinispanSpringBootTestResource {

        public Conditional() {
            super();
            enableConditional();
        }
    }

}
