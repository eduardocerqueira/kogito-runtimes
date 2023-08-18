package org.kie.kogito.codegen.decision.config;

import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.codegen.core.AbstractConfigGenerator;

public class DecisionConfigGenerator extends AbstractConfigGenerator {

    public DecisionConfigGenerator(KogitoBuildContext context) {
        super(context, "DecisionConfig");
    }
}
