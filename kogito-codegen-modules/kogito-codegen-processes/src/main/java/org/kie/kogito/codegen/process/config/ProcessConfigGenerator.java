package org.kie.kogito.codegen.process.config;

import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.codegen.core.AbstractConfigGenerator;

public class ProcessConfigGenerator extends AbstractConfigGenerator {

    public ProcessConfigGenerator(KogitoBuildContext context) {
        super(context, "ProcessConfig");
    }
}
