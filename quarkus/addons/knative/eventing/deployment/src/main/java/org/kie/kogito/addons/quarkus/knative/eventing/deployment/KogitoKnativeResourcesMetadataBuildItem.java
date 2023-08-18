package org.kie.kogito.addons.quarkus.knative.eventing.deployment;

import java.util.Collections;
import java.util.Set;

import org.kie.kogito.event.cloudevents.CloudEventMeta;

import io.quarkus.builder.item.SimpleBuildItem;

/**
 * Build Item to hold information about the project to generate Knative resources
 */
public final class KogitoKnativeResourcesMetadataBuildItem extends SimpleBuildItem {

    private final Set<? extends CloudEventMeta> cloudEvents;
    private final KogitoServiceDeploymentTarget deployment;

    public KogitoKnativeResourcesMetadataBuildItem(final Set<? extends CloudEventMeta> cloudEvents, final KogitoServiceDeploymentTarget deployment) {
        this.cloudEvents = cloudEvents;
        this.deployment = deployment;
    }

    public Set<CloudEventMeta> getCloudEvents() {
        return Collections.unmodifiableSet(this.cloudEvents);
    }

    public KogitoServiceDeploymentTarget getDeployment() {
        return deployment;
    }
}
