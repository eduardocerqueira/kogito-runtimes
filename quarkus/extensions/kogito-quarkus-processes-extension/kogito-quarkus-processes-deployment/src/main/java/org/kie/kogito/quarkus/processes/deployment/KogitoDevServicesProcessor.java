package org.kie.kogito.quarkus.processes.deployment;

import org.kie.kogito.quarkus.processes.devservices.DevModeWorkflowLogger;
import org.kie.kogito.quarkus.workflow.deployment.AbstractDevServicesProcessor;
import org.kie.kogito.quarkus.workflow.deployment.config.KogitoWorkflowBuildTimeConfig;

import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.arc.processor.DotNames;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.LaunchModeBuildItem;

/**
 * Starts a Data Index as dev service if needed.
 */
public class KogitoDevServicesProcessor extends AbstractDevServicesProcessor {

    @BuildStep
    public void logger(BuildProducer<AdditionalBeanBuildItem> additionalBean, LaunchModeBuildItem launchMode, KogitoWorkflowBuildTimeConfig config) {
        if (shouldInclude(launchMode, config)) {
            additionalBean.produce(AdditionalBeanBuildItem.builder().addBeanClass(DevModeWorkflowLogger.class).setUnremovable().setDefaultScope(DotNames.APPLICATION_SCOPED).build());
        }
    }

}
