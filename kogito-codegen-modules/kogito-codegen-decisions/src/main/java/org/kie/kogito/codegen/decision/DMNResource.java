package org.kie.kogito.codegen.decision;

import java.nio.file.Path;

import org.kie.dmn.api.core.DMNModel;
import org.kie.kogito.codegen.api.io.CollectedResource;

/**
 * A (DMNModel, CollectedResource) pair
 */
public class DMNResource {
    private final DMNModel dmnModel;
    private final CollectedResource collectedResource;

    public DMNResource(DMNModel dmnModel, CollectedResource collectedResource) {
        this.dmnModel = dmnModel;
        this.collectedResource = collectedResource;
    }

    public DMNModel getDmnModel() {
        return dmnModel;
    }

    public Path getPath() {
        return collectedResource.basePath();
    }

    public CollectedResource getCollectedResource() {
        return collectedResource;
    }
}
