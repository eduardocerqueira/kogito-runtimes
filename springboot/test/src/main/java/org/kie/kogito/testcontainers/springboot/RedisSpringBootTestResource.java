package org.kie.kogito.testcontainers.springboot;

import java.util.Map;

import org.kie.kogito.test.resources.ConditionalSpringBootTestResource;
import org.kie.kogito.testcontainers.KogitoRedisSearchContainer;

import static java.util.Collections.singletonMap;

public class RedisSpringBootTestResource extends ConditionalSpringBootTestResource {

    public static final String KOGITO_REDIS_URL = "kogito.persistence.redis.url";

    public RedisSpringBootTestResource() {
        super(new KogitoRedisSearchContainer());
    }

    @Override
    protected Map<String, String> getProperties() {
        return singletonMap(KOGITO_REDIS_URL, "http://localhost:" + getTestResource().getMappedPort());
    }

    public static class Conditional extends RedisSpringBootTestResource {

        public Conditional() {
            super();
            enableConditional();
        }
    }
}
