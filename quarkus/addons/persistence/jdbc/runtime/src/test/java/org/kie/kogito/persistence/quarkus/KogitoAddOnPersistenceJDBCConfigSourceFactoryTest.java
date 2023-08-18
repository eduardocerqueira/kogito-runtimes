package org.kie.kogito.persistence.quarkus;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.microprofile.config.spi.ConfigSource;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.kie.kogito.persistence.quarkus.KogitoAddOnPersistenceJDBCConfigSourceFactory.FLYWAY_LOCATIONS;
import static org.kie.kogito.persistence.quarkus.KogitoAddOnPersistenceJDBCConfigSourceFactory.POSTGRESQL;

class KogitoAddOnPersistenceJDBCConfigSourceFactoryTest {

    private final static KogitoAddOnPersistenceJDBCConfigSourceFactory factory = new KogitoAddOnPersistenceJDBCConfigSourceFactory();

    @Test
    void getConfigSourcesInternalExistingLocations() {
        Set<String> locationsSet = getLocationsSet(getConfigSource(POSTGRESQL, "/path/to/locations", Integer.MAX_VALUE)
                .getValue(FLYWAY_LOCATIONS));

        assertThat(locationsSet).containsExactlyInAnyOrder("/path/to/locations", "classpath:/db/postgresql");
    }

    @Test
    void getConfigSourcesInternalDefaultLocations() {
        Set<String> locationsSet = getLocationsSet(getConfigSource(POSTGRESQL, "/path/to/locations", Integer.MIN_VALUE)
                .getValue(FLYWAY_LOCATIONS));

        assertThat(locationsSet).containsOnly("classpath:/db/postgresql");
    }

    @Test
    void getConfigSourcesInternalNoExistingLocations() {
        Set<String> locationsSet = getLocationsSet(getConfigSource(POSTGRESQL, null, Integer.MAX_VALUE)
                .getValue(FLYWAY_LOCATIONS));

        assertThat(locationsSet).containsExactlyInAnyOrder("classpath:/db/postgresql");
    }

    @Test
    void getConfigSourcesInternalDatabaseNameEmpty() {
        ConfigSource configSource = getConfigSource(null, "/path/to/locations", Integer.MAX_VALUE);
        assertThat(configSource.getPropertyNames()).isEmpty();
    }

    @Test
    void getConfigSourcesInternalEnsuresNoDuplication() {
        Set<String> locationsSet = getLocationsSet(getConfigSource(POSTGRESQL, "classpath:/db/postgresql", Integer.MAX_VALUE)
                .getValue(FLYWAY_LOCATIONS));

        assertThat(locationsSet).containsExactlyInAnyOrder("classpath:/db/postgresql");
    }

    private static ConfigSource getConfigSource(String databaseName, String flywayLocationsValue,
            int flywayLocationsConfigSourceOrdinal) {
        Iterable<ConfigSource> configSources = factory.getConfigSourcesInternal(databaseName, flywayLocationsValue,
                flywayLocationsConfigSourceOrdinal);
        assertThat(configSources).hasSize(1);
        return configSources.iterator().next();
    }

    private static Set<String> getLocationsSet(String locations) {
        return Arrays.stream(locations.split(",")).collect(Collectors.toSet());
    }
}