package org.kie.kogito.decision;

import org.kie.kogito.KogitoEngine;

public interface DecisionModels extends KogitoEngine {

    DecisionModel getDecisionModel(String namespace, String name);
}
