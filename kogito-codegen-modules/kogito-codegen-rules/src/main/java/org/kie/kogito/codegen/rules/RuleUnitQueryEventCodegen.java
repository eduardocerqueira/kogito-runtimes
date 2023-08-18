package org.kie.kogito.codegen.rules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.drools.codegen.common.GeneratedFile;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;

public class RuleUnitQueryEventCodegen {

    private final KogitoBuildContext context;
    private final Iterable<QueryGenerator> ruleUnitGenerators;

    public RuleUnitQueryEventCodegen(KogitoBuildContext context, Iterable<QueryGenerator> ruleUnitGenerators) {
        this.context = context;
        this.ruleUnitGenerators = ruleUnitGenerators;
    }

    Collection<GeneratedFile> generate() {
        List<GeneratedFile> generatedFiles = new ArrayList<>();
        if (context.getAddonsConfig().useEventDrivenRules()) {
            for (QueryGenerator queryGenerator : ruleUnitGenerators) {
                generatedFiles.add(new QueryEventDrivenExecutorGenerator(queryGenerator).generate());
            }
        }
        return generatedFiles;
    }

}
