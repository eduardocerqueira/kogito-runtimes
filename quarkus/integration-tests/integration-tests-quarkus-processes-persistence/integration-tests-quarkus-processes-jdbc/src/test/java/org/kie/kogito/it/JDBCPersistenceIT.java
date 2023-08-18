package org.kie.kogito.it;

import org.kie.kogito.testcontainers.quarkus.PostgreSqlQuarkusTestResource;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusIntegrationTest;

@QuarkusIntegrationTest
@QuarkusTestResource(PostgreSqlQuarkusTestResource.class)
class JDBCPersistenceIT extends PersistenceTest {

}
