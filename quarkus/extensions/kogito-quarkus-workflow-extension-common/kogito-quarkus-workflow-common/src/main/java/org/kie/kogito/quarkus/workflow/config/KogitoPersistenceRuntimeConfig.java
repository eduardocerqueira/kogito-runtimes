package org.kie.kogito.quarkus.workflow.config;

import java.util.Optional;

import io.quarkus.runtime.annotations.ConfigGroup;
import io.quarkus.runtime.annotations.ConfigItem;

@ConfigGroup
public class KogitoPersistenceRuntimeConfig {

    /**
     * Persistence DB type
     */
    @ConfigItem
    public Optional<PersistenceType> type;

    /**
     * Automatically apply database schema changes
     */
    @ConfigItem(name = "auto.ddl", defaultValue = "true")
    public boolean autoDDL;

    /**
     * Use optimistic locking
     */
    @ConfigItem(name = "optimistic.lock", defaultValue = "false")
    public boolean optimisticLock;

    /**
     * Query execution timeout
     */
    @ConfigItem(name = "query.timeout.millis", defaultValue = "10000")
    public long queryTimeout;

}
