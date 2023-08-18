package org.kie.kogito.decision;

import java.util.Optional;

import org.kie.dmn.api.core.DMNContext;
import org.kie.kogito.ExecutionIdSupplier;

public class DecisionExecutionIdUtils {

    private DecisionExecutionIdUtils() {

    }

    private static final String EXECUTION_ID_KEY = "__kogito_execution_id__";

    public static String get(DMNContext context) {
        return getOptional(context).orElse(null);
    }

    public static Optional<String> getOptional(DMNContext context) {
        return Optional.ofNullable(context)
                .map(DMNContext::getMetadata)
                .map(metadata -> metadata.get(EXECUTION_ID_KEY))
                .map(Object::toString);
    }

    public static DMNContext inject(DMNContext context, ExecutionIdSupplier executionIdSupplier) {
        context.getMetadata().set(EXECUTION_ID_KEY, executionIdSupplier.get());
        return context;
    }
}
