package org.kie.kogito.quarkus.addons.common.deployment;

import java.util.List;
import java.util.stream.Collectors;

import io.quarkus.deployment.Capabilities;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.Produce;
import io.quarkus.deployment.pkg.builditem.ArtifactResultBuildItem;

import static java.util.Arrays.asList;

/**
 * Abstract class for Add-Ons processors that requires a set of capabilities to be presented.
 * <p/>
 * When extending this base class, if your add-on requires a particular set of capabilities,
 * inform them in the constructor. For example:
 * <p/>
 * 
 * <pre>
 * public MyKogitoAddonProcessor() {
 *     super(KogitoCapability.RULES);
 * }
 * </pre>
 *
 * If your add-on doesn't require a particular set of {@link KogitoCapability}, use {@link AnyEngineKogitoAddOnProcessor}
 * instead to verify if at least one engine is presented.
 *
 * @see <a href="https://quarkus.io/guides/capabilities">Quarkus Extension Capabilities</a>
 */
public abstract class RequireCapabilityKogitoAddOnProcessor {

    private final List<KogitoCapability> requiredCapabilities;

    /**
     * Required capabilities that this Add-On depends on.
     * Add at least one capability to the list, otherwise an {@link IllegalArgumentException} will be raised.
     *
     * @see <a href="https://quarkus.io/guides/capabilities#declaring-capabilities">Declaring Capabilities</a>
     */
    public RequireCapabilityKogitoAddOnProcessor(final KogitoCapability... requiredCapabilities) {
        if (requiredCapabilities == null || requiredCapabilities.length == 0) {
            throw new IllegalArgumentException("Please set at least one capability");
        }
        this.requiredCapabilities = asList(requiredCapabilities);
    }

    /**
     * {@link BuildStep} to verify if all {@link Capabilities} are presented in the current classpath.
     *
     */
    @BuildStep
    @Produce(ArtifactResultBuildItem.class)
    void verifyCapabilities(final Capabilities capabilities) {
        final List<KogitoCapability> missing = requiredCapabilities.stream()
                .filter(kc -> capabilities.isMissing(kc.getCapability()))
                .collect(Collectors.toList());
        if (!missing.isEmpty()) {
            throw this.exceptionForRequiredCapabilities(missing);
        }
    }

    private IllegalStateException exceptionForRequiredCapabilities(List<KogitoCapability> missingCapabilities) {
        final StringBuilder sb = new StringBuilder();
        sb.append("The following capabilities are missing: \n");
        missingCapabilities.forEach(c -> {
            sb.append("\t - ").append(c.getCapability()).append("\n");
            sb.append("\t\t offered by the artifact ")
                    .append(KogitoCapability.KOGITO_GROUP_ID)
                    .append(":")
                    .append(c.getOfferedBy())
                    .append("\n");
        });
        sb.append("Add the above artifacts in your project's pom.xml file");
        return new IllegalStateException(sb.toString());
    }

}
