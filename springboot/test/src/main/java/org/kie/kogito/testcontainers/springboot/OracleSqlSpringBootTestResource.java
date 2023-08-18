package org.kie.kogito.testcontainers.springboot;

import java.util.HashMap;
import java.util.Map;

import org.kie.kogito.test.resources.ConditionalSpringBootTestResource;
import org.kie.kogito.testcontainers.KogitoOracleSqlContainer;

/**
 * Oracle SQL Springboot resource that works within the test lifecycle.
 *
 */
public class OracleSqlSpringBootTestResource extends ConditionalSpringBootTestResource<KogitoOracleSqlContainer> {

    public static final String SPRING_DATASOURCE_URL = "spring.datasource.url";
    public static final String SPRING_DATASOURCE_USERNAME = "spring.datasource.username";
    public static final String SPRING_DATASOURCE_PASSWORD = "spring.datasource.password";

    public OracleSqlSpringBootTestResource() {
        super(new KogitoOracleSqlContainer());
    }

    @Override
    protected Map<String, String> getProperties() {
        Map<String, String> properties = new HashMap<>();
        properties.put(SPRING_DATASOURCE_URL, getTestResource().getJdbcUrl());
        properties.put(SPRING_DATASOURCE_USERNAME, getTestResource().getUsername());
        properties.put(SPRING_DATASOURCE_PASSWORD, getTestResource().getPassword());
        return properties;
    }

    public static class Conditional extends OracleSqlSpringBootTestResource {

        public Conditional() {
            enableConditional();
        }
    }
}
