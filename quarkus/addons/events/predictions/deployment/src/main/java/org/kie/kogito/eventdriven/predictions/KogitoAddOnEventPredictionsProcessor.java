package org.kie.kogito.eventdriven.predictions;

import org.kie.kogito.quarkus.addons.common.deployment.KogitoCapability;
import org.kie.kogito.quarkus.addons.common.deployment.RequireCapabilityKogitoAddOnProcessor;

import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;

class KogitoAddOnEventPredictionsProcessor extends RequireCapabilityKogitoAddOnProcessor {

    private static final String FEATURE = "kogito-addon-events-predictions-extension";

    KogitoAddOnEventPredictionsProcessor() {
        super(KogitoCapability.PREDICTIONS);
    }

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

}
