package org.kie.kogito.monitoring.elastic.quarkus.deployment;

import org.kie.kogito.quarkus.addons.common.deployment.AnyEngineKogitoAddOnProcessor;

import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;

class KogitoAddOnMonitoringElasticProcessor extends AnyEngineKogitoAddOnProcessor {

    private static final String FEATURE = "kogito-addon-monitoring-elastic-extension";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

}
