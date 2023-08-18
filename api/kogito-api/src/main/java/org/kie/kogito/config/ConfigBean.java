package org.kie.kogito.config;

import java.util.Optional;

import org.kie.kogito.KogitoConfig;
import org.kie.kogito.KogitoGAV;

public interface ConfigBean extends KogitoConfig {

    boolean useCloudEvents();

    default boolean failOnEmptyBean() {
        return false;
    }

    String getServiceUrl();

    Optional<KogitoGAV> getGav();

    default short processInstanceLimit() {
        return 1000;
    }
}
