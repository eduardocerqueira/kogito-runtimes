package org.kie.kogito.quarkus.extensions.spi.deployment;

import java.util.function.BooleanSupplier;

/**
 * Can be used to execute a BuildItem `onlyIf` the Process extension is presented.
 * Yet, another way of implementing this feature is to use the capability feature.
 * But in this case, it would require the `BuildItem` to be presented and/or the method to be executed
 * to add the conditional verifying the capability.
 *
 * @see <a href="https://quarkus.io/guides/writing-extensions#conditional-step-inclusion">Conditional Step Inclusion</a>
 * @see <a href="https://quarkus.io/guides/writing-extensions#capabilities">Capabilities</a>
 */
public class HasProcessExtension implements BooleanSupplier {

    private static final String PROCESS_EXTENSION_CLASS = "org.kie.kogito.quarkus.processes.deployment.ProcessesAssetsProcessor";

    @Override
    public boolean getAsBoolean() {
        try {
            Class.forName(PROCESS_EXTENSION_CLASS);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
