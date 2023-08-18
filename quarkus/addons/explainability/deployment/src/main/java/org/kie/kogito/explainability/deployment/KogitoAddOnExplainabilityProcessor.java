package org.kie.kogito.explainability.deployment;

import org.kie.kogito.quarkus.addons.common.deployment.KogitoCapability;
import org.kie.kogito.quarkus.addons.common.deployment.RequireCapabilityKogitoAddOnProcessor;

import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;

class KogitoAddOnExplainabilityProcessor extends RequireCapabilityKogitoAddOnProcessor {

    private static final String FEATURE = "kogito-addon-explainability-extension";

    KogitoAddOnExplainabilityProcessor() {
        super(KogitoCapability.DECISIONS);
    }

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

}
