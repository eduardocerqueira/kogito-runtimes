package org.kie.kogito.explainability;

import java.io.InputStreamReader;

import org.kie.dmn.api.core.DMNRuntime;
import org.kie.kogito.Application;
import org.kie.kogito.Config;
import org.kie.kogito.KogitoEngine;
import org.kie.kogito.decision.DecisionModels;
import org.kie.kogito.dmn.DMNKogito;
import org.kie.kogito.dmn.DmnDecisionModel;
import org.kie.kogito.uow.UnitOfWorkManager;

public class ApplicationMock implements Application {

    final static DMNRuntime genericDMNRuntime = DMNKogito.createGenericDMNRuntime(new InputStreamReader(
            ApplicationMock.class.getResourceAsStream(Constants.MODEL_RESOURCE)));

    final static DecisionModels decisionModels;

    static {
        DmnDecisionModel decisionModel = new DmnDecisionModel(genericDMNRuntime, Constants.MODEL_NAMESPACE, Constants.MODEL_NAME, () -> Constants.TEST_EXECUTION_ID);

        decisionModels = (namespace, name) -> {
            if (Constants.MODEL_NAMESPACE.equals(namespace) && Constants.MODEL_NAME.equals(name)) {
                return decisionModel;
            }
            throw new RuntimeException("Model " + namespace + ":" + name + " not found.");
        };
    }

    @Override
    public Config config() {
        return null;
    }

    @Override
    public UnitOfWorkManager unitOfWorkManager() {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends KogitoEngine> T get(Class<T> clazz) {
        return (T) decisionModels;
    }
}
