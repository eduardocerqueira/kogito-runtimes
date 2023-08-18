package org.kie.kogito.persistence.quarkus;

import java.util.Map;
import java.util.Set;

import org.eclipse.microprofile.config.spi.ConfigSource;

public class KogitoAddOnPersistenceJDBCConfigSource implements ConfigSource {

    static final Integer ORDINAL = 500;

    private final Map<String, String> configuration;

    public KogitoAddOnPersistenceJDBCConfigSource(Map<String, String> configuration) {
        this.configuration = configuration;
    }

    @Override
    public Set<String> getPropertyNames() {
        return configuration.keySet();
    }

    @Override
    public int getOrdinal() {
        return ORDINAL;
    }

    @Override
    public String getValue(String propertyName) {
        return configuration.get(propertyName);
    }

    @Override
    public String getName() {
        return KogitoAddOnPersistenceJDBCConfigSource.class.getSimpleName();
    }
}
