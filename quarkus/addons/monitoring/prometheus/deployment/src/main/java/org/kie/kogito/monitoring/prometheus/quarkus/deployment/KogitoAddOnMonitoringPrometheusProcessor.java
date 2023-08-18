package org.kie.kogito.monitoring.prometheus.quarkus.deployment;

import org.kie.kogito.quarkus.addons.common.deployment.AnyEngineKogitoAddOnProcessor;

import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;

class KogitoAddOnMonitoringPrometheusProcessor extends AnyEngineKogitoAddOnProcessor {

    private static final String FEATURE = "kogito-addon-monitoring-prometheus-extension";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

}
