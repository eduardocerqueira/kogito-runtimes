package org.kie.kogito.addons.quarkus.camel.runtime;

import java.util.Map;

import io.smallrye.config.common.MapBackedConfigSource;

public class CamelConfigSource extends MapBackedConfigSource {

    static final Integer ORDINAL = Integer.MIN_VALUE;

    public CamelConfigSource(Map<String, String> configuration) {
        super(CamelConfigSource.class.getSimpleName(), configuration, ORDINAL);
    }
}
