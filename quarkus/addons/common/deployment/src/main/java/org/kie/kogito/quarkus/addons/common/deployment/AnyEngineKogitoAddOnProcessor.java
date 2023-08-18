package org.kie.kogito.quarkus.addons.common.deployment;

import io.quarkus.deployment.Capabilities;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.Produce;
import io.quarkus.deployment.pkg.builditem.ArtifactResultBuildItem;

/**
 * You don't need to point to a particular engine if your add-on fits any set of it.
 * Just by extending this class, it will make any of {@link KogitoCapability#ENGINES} to be presented.
 * <p/>
 * If you need a particular set of {@link KogitoCapability} to be presented, please use {@link RequireCapabilityKogitoAddOnProcessor} instead.
 *
 * @see <a href="https://quarkus.io/guides/capabilities">Quarkus Extension Capabilities</a>
 */
public abstract class AnyEngineKogitoAddOnProcessor {

    /**
     * Verifies if one of the {@link KogitoCapability#ENGINES} are present in the classpath.
     * 
     * @param capabilities
     */
    @BuildStep
    @Produce(ArtifactResultBuildItem.class)
    void verifyCapabilities(final Capabilities capabilities) {
        if (KogitoCapability.ENGINES.stream().noneMatch(kc -> capabilities.isPresent(kc.getCapability()))) {
            throw this.exceptionForEngineNotPresent();
        }
    }

    private IllegalStateException exceptionForEngineNotPresent() {
        final StringBuilder sb = new StringBuilder();
        sb.append("This Kogito Quarkus Add-on requires at least one of the following Kogito Extensions: \n");
        KogitoCapability.ENGINES.forEach(c -> {
            sb.append("\t - ").append(c.getCapability()).append("\n");
            sb.append("\t\t offered by the artifact ")
                    .append(KogitoCapability.KOGITO_GROUP_ID)
                    .append(":")
                    .append(c.getOfferedBy())
                    .append("\n");
        });
        sb.append("Add one of the above artifacts in your project's pom.xml file");
        return new IllegalStateException(sb.toString());
    }
}
