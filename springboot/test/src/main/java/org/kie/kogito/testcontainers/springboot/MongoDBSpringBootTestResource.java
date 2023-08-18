package org.kie.kogito.testcontainers.springboot;

import java.util.Map;

import org.kie.kogito.test.resources.ConditionalSpringBootTestResource;
import org.kie.kogito.testcontainers.KogitoMongoDBContainer;

import static java.util.Collections.singletonMap;

/**
 * MongoDB spring boot resource that works within the test lifecycle.
 *
 */
public class MongoDBSpringBootTestResource extends ConditionalSpringBootTestResource<KogitoMongoDBContainer> {

    public static final String MONGODB_CONNECTION_PROPERTY = "spring.data.mongodb.uri";

    public MongoDBSpringBootTestResource() {
        super(new KogitoMongoDBContainer());
    }

    @Override
    protected Map<String, String> getProperties() {
        return singletonMap(MONGODB_CONNECTION_PROPERTY, getTestResource().getReplicaSetUrl());
    }

    public static class Conditional extends MongoDBSpringBootTestResource {

        public Conditional() {
            super();
            enableConditional();
        }
    }
}
