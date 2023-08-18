package org.kie.kogito.testcontainers.quarkus;

import java.util.HashMap;
import java.util.Map;

import org.kie.kogito.test.resources.ConditionalQuarkusTestResource;
import org.kie.kogito.testcontainers.KogitoPostgreSqlContainer;

/**
 * PostgreSQL quarkus resource that works within the test lifecycle.
 */
public class PostgreSqlQuarkusTestResource extends ConditionalQuarkusTestResource<KogitoPostgreSqlContainer> {

    public static final String QUARKUS_DATASOURCE_REACTIVE_URL = "quarkus.datasource.reactive.url";
    public static final String QUARKUS_DATASOURCE_JDBC_URL = "quarkus.datasource.jdbc.url";
    public static final String QUARKUS_DATASOURCE_USERNAME = "quarkus.datasource.username";
    public static final String QUARKUS_DATASOURCE_PASSWORD = "quarkus.datasource.password";

    public PostgreSqlQuarkusTestResource() {
        super(new KogitoPostgreSqlContainer());
    }

    @Override
    protected Map<String, String> getProperties() {
        Map<String, String> properties = new HashMap<>();
        properties.put(QUARKUS_DATASOURCE_REACTIVE_URL, getTestResource().getReactiveUrl());
        properties.put(QUARKUS_DATASOURCE_JDBC_URL, getTestResource().getJdbcUrl());
        properties.put(QUARKUS_DATASOURCE_USERNAME, getTestResource().getUsername());
        properties.put(QUARKUS_DATASOURCE_PASSWORD, getTestResource().getPassword());
        return properties;
    }

    public static class Conditional extends PostgreSqlQuarkusTestResource {

        public Conditional() {
            enableConditional();
        }
    }
}