package org.kie.kogito.tracing.decision.quarkus.deployment;

import org.kie.kogito.quarkus.addons.common.deployment.KogitoCapability;
import org.kie.kogito.quarkus.addons.common.deployment.RequireCapabilityKogitoAddOnProcessor;

import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;

class KogitoAddOnTracingDecisionProcessor extends RequireCapabilityKogitoAddOnProcessor {

    private static final String FEATURE = "kogito-addon-tracing-decision-extension";

    KogitoAddOnTracingDecisionProcessor() {
        super(KogitoCapability.DECISIONS);
    }

    @BuildStep
    @SuppressWarnings("unused")
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

}
