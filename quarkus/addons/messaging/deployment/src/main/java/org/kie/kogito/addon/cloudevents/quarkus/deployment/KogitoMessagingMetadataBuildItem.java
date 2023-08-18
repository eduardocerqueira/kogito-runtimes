package org.kie.kogito.addon.cloudevents.quarkus.deployment;

import java.util.Map;

import org.jboss.jandex.DotName;

import io.quarkus.builder.item.SimpleBuildItem;

public final class KogitoMessagingMetadataBuildItem extends SimpleBuildItem {

    private final Map<DotName, EventGenerator> generators;

    public KogitoMessagingMetadataBuildItem(Map<DotName, EventGenerator> generators) {
        this.generators = generators;
    }

    public Map<DotName, EventGenerator> generators() {
        return generators;
    }
}
