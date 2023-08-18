package org.kie.kogito.codegen.prediction.config;

import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.codegen.core.AbstractConfigGenerator;

public class PredictionConfigGenerator extends AbstractConfigGenerator {

    public PredictionConfigGenerator(KogitoBuildContext context) {
        super(context, "PredictionConfig");
    }
}
