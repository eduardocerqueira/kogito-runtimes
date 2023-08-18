package org.kie.kogito.codegen.rules.config;

import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.codegen.core.AbstractConfigGenerator;

public class RuleConfigGenerator extends AbstractConfigGenerator {

    public RuleConfigGenerator(KogitoBuildContext context) {
        super(context, "RuleConfig");
    }
}
