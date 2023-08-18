package org.kie.kogito.testcontainers.springboot;

import java.util.HashMap;
import java.util.Map;

import org.kie.kogito.test.resources.ConditionalSpringBootTestResource;
import org.kie.kogito.testcontainers.KogitoPostgreSqlContainer;

import static org.kie.kogito.testcontainers.KogitoPostgreSqlContainer.POSTGRESQL_CONNECTION_URI;

/**
 * PostgreSQL spring boot resource that works within the test lifecycle.
 */
public class PostgreSqlSpringBootTestResource extends ConditionalSpringBootTestResource<KogitoPostgreSqlContainer> {

    public static final String SPRING_DATASOURCE_URL = "spring.datasource.url";
    public static final String SPRING_DATASOURCE_USERNAME = "spring.datasource.username";
    public static final String SPRING_DATASOURCE_PASSWORD = "spring.datasource.password";
    public static final String SPRING_FLYWAY_URL = "spring.flyway.url";
    public static final String SPRING_FLYWAY_USERNAME = "spring.flyway.user";
    public static final String SPRING_FLYWAY_PASSWORD = "spring.flyway.password";

    public PostgreSqlSpringBootTestResource() {
        super(new KogitoPostgreSqlContainer());
    }

    @Override
    protected Map<String, String> getProperties() {
        Map<String, String> properties = new HashMap<>();
        properties.put(POSTGRESQL_CONNECTION_URI, getTestResource().getReactiveUrl());
        properties.put(SPRING_DATASOURCE_URL, getTestResource().getJdbcUrl());
        properties.put(SPRING_DATASOURCE_USERNAME, getTestResource().getUsername());
        properties.put(SPRING_DATASOURCE_PASSWORD, getTestResource().getPassword());
        properties.put(SPRING_FLYWAY_URL, getTestResource().getJdbcUrl());
        properties.put(SPRING_FLYWAY_USERNAME, getTestResource().getUsername());
        properties.put(SPRING_FLYWAY_PASSWORD, getTestResource().getPassword());
        return properties;
    }

    public static class Conditional extends PostgreSqlSpringBootTestResource {

        public Conditional() {
            enableConditional();
        }
    }
}
