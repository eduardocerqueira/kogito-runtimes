package org.kie.kogito.quarkus.serverless.workflow.deployment;

import org.kie.kogito.quarkus.workflow.deployment.AbstractDevServicesProcessor;
import org.kie.kogito.quarkus.workflow.deployment.config.KogitoWorkflowBuildTimeConfig;
import org.kie.kogito.serverless.workflow.devservices.DevModeServerlessWorkflowLogger;

import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.arc.processor.DotNames;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.LaunchModeBuildItem;

public class ServerlessWorkflowDevServicesProcessor extends AbstractDevServicesProcessor {
    @BuildStep
    public void logger(BuildProducer<AdditionalBeanBuildItem> additionalBean, LaunchModeBuildItem launchMode, KogitoWorkflowBuildTimeConfig config) {
        if (shouldInclude(launchMode, config)) {
            additionalBean.produce(AdditionalBeanBuildItem.builder().addBeanClass(DevModeServerlessWorkflowLogger.class).setUnremovable().setDefaultScope(DotNames.APPLICATION_SCOPED).build());
        }
    }

}
