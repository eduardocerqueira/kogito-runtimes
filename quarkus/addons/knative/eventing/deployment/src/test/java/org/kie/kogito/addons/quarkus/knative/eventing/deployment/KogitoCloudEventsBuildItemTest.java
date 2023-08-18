package org.kie.kogito.addons.quarkus.knative.eventing.deployment;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.kie.kogito.codegen.process.events.ProcessCloudEventMeta;

import static org.assertj.core.api.Assertions.assertThat;

class KogitoCloudEventsBuildItemTest {

    @Test
    void getCloudEvents() {
        Set<ProcessCloudEventMeta> cloudEvents = new HashSet<>();
        KogitoCloudEventsBuildItem buildItem = new KogitoCloudEventsBuildItem(cloudEvents);
        assertThat(buildItem.getCloudEvents()).isSameAs(cloudEvents);
    }
}
