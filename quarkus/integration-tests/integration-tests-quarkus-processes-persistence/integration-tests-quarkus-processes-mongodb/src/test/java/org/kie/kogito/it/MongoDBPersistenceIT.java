package org.kie.kogito.it;

import org.kie.kogito.testcontainers.quarkus.MongoDBQuarkusTestResource;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusIntegrationTest;

@QuarkusIntegrationTest
@QuarkusTestResource(MongoDBQuarkusTestResource.class)
class MongoDBPersistenceIT extends PersistenceTest {
}
