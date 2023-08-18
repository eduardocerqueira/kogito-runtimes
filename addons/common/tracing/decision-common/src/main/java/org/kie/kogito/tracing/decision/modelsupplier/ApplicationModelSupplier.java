package org.kie.kogito.tracing.decision.modelsupplier;

import java.util.function.BiFunction;

import org.kie.dmn.api.core.DMNModel;
import org.kie.kogito.Application;
import org.kie.kogito.decision.DecisionModels;

public class ApplicationModelSupplier implements BiFunction<String, String, DMNModel> {

    private final Application application;

    public ApplicationModelSupplier(Application application) {
        this.application = application;
    }

    @Override
    public DMNModel apply(String namespace, String name) {
        if (namespace == null || name == null) {
            return null;
        }
        return application.get(DecisionModels.class).getDecisionModel(namespace, name).getDMNModel();
    }

}
