package org.kie.kogito.jobs.messaging.quarkus.deployment;

import java.util.ArrayList;
import java.util.List;

import org.kie.kogito.quarkus.addons.common.deployment.KogitoCapability;
import org.kie.kogito.quarkus.addons.common.deployment.OneOfCapabilityKogitoAddOnProcessor;

import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.builditem.nativeimage.ReflectiveClassBuildItem;

class KogitoAddOnJobsMessagingProcessor extends OneOfCapabilityKogitoAddOnProcessor {

    private static final String FEATURE = "kogito-addon-jobs-messaging-extension";

    KogitoAddOnJobsMessagingProcessor() {
        super(KogitoCapability.PROCESSES, KogitoCapability.SERVERLESS_WORKFLOW);
    }

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    public ReflectiveClassBuildItem jobsApiReflection() {
        List<Class<?>> reflectiveClasses = new ArrayList<>();
        reflectiveClasses.addAll(org.kie.kogito.jobs.api.utils.ReflectionUtils.apiReflectiveClasses());
        reflectiveClasses.addAll(org.kie.kogito.jobs.service.api.utils.ReflectionUtils.apiReflectiveClasses());
        return new ReflectiveClassBuildItem(true,
                true,
                true,
                reflectiveClasses.toArray(new Class[] {}));
    }
}
