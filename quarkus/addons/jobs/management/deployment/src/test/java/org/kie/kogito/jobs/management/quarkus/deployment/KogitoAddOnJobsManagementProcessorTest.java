package org.kie.kogito.jobs.management.quarkus.deployment;

import org.junit.jupiter.api.Test;

import io.quarkus.deployment.builditem.nativeimage.ReflectiveClassBuildItem;

import static org.assertj.core.api.Assertions.assertThat;

class KogitoAddOnJobsManagementProcessorTest {

    private final KogitoAddOnJobsManagementProcessor processor = new KogitoAddOnJobsManagementProcessor();

    @Test
    void feature() {
        assertThat(processor.feature()).isNotNull();
        assertThat(processor.feature().getName()).isEqualTo("kogito-addon-jobs-management-extension");
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
