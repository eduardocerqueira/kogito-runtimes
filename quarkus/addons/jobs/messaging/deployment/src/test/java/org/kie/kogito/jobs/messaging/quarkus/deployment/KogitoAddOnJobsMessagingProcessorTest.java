package org.kie.kogito.jobs.messaging.quarkus.deployment;

import org.junit.jupiter.api.Test;

import io.quarkus.deployment.builditem.nativeimage.ReflectiveClassBuildItem;

import static org.assertj.core.api.Assertions.assertThat;

class KogitoAddOnJobsMessagingProcessorTest {

    private final KogitoAddOnJobsMessagingProcessor processor = new KogitoAddOnJobsMessagingProcessor();

    @Test
    void feature() {
        assertThat(processor.feature()).isNotNull();
        assertThat(processor.feature().getName()).isEqualTo("kogito-addon-jobs-messaging-extension");
    }

    @Test
    void jobsApiReflection() {
        ReflectiveClassBuildItem buildItem = processor.jobsApiReflection();
        assertThat(buildItem.isConstructors()).isTrue();
        assertThat(buildItem.isMethods()).isTrue();
        assertThat(buildItem.isFields()).isTrue();
        assertThat(buildItem.getClassNames()).hasSize(21);
    }
}
