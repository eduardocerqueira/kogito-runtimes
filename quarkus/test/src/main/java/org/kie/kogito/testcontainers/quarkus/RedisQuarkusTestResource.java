package org.kie.kogito.testcontainers.quarkus;

import java.util.Map;

import org.kie.kogito.test.resources.ConditionalQuarkusTestResource;
import org.kie.kogito.testcontainers.KogitoRedisSearchContainer;

import static java.util.Collections.singletonMap;

public class RedisQuarkusTestResource extends ConditionalQuarkusTestResource {

    public static final String KOGITO_REDIS_URL = "kogito.persistence.redis.url";

    public RedisQuarkusTestResource() {
        super(new KogitoRedisSearchContainer());
    }

    @Override
    protected Map<String, String> getProperties() {
        return singletonMap(KOGITO_REDIS_URL, "http://" + getServerUrl());
    }

    public static class Conditional extends RedisQuarkusTestResource {

        public Conditional() {
            super();
            enableConditional();
        }
    }
}
