package org.kie.kogito.addons.quarkus.knative.eventing.deployment;

import java.util.Set;

import org.kie.kogito.event.cloudevents.CloudEventMeta;

import io.quarkus.builder.item.MultiBuildItem;

public final class KogitoCloudEventsBuildItem extends MultiBuildItem {

    private final Set<? extends CloudEventMeta> cloudEvents;

    public KogitoCloudEventsBuildItem(Set<? extends CloudEventMeta> cloudEvents) {
        this.cloudEvents = cloudEvents;
    }

    public Set<? extends CloudEventMeta> getCloudEvents() {
        return cloudEvents;
    }
}
