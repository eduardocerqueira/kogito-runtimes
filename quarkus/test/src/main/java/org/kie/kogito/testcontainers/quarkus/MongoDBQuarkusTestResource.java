package org.kie.kogito.testcontainers.quarkus;

import java.util.Map;

import org.kie.kogito.test.resources.ConditionalQuarkusTestResource;
import org.kie.kogito.testcontainers.KogitoMongoDBContainer;

import static java.util.Collections.singletonMap;

/**
 * MongoDB quarkus resource that works within the test lifecycle.
 *
 */
public class MongoDBQuarkusTestResource extends ConditionalQuarkusTestResource<KogitoMongoDBContainer> {

    public static final String MONGODB_CONNECTION_PROPERTY = "quarkus.mongodb.connection-string";

    public MongoDBQuarkusTestResource() {
        super(new KogitoMongoDBContainer());
    }

    @Override
    protected Map<String, String> getProperties() {
        return singletonMap(MONGODB_CONNECTION_PROPERTY, getTestResource().getReplicaSetUrl());
    }

    public static class Conditional extends MongoDBQuarkusTestResource {

        public Conditional() {
            super();
            enableConditional();
        }
    }
}
