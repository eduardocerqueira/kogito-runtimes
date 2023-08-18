package org.kie.kogito.quarkus.addons.common.deployment;

/**
 * A mock add-on that requires serverless workflow or processes
 */
public class RequireOneEngineAddonProcessor extends OneOfCapabilityKogitoAddOnProcessor {

    public RequireOneEngineAddonProcessor() {
        super(KogitoCapability.SERVERLESS_WORKFLOW, KogitoCapability.PROCESSES);
    }

}
