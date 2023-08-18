package org.kie.kogito.quarkus.addons.common.deployment;

/**
 * A mock add-on that requires decisions and predictions
 */
public class RequireEngineAddonProcessor extends RequireCapabilityKogitoAddOnProcessor {

    public RequireEngineAddonProcessor() {
        super(KogitoCapability.DECISIONS, KogitoCapability.PREDICTIONS);
    }

}
