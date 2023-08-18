package org.kie.kogito.codegen.rules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.drools.codegen.common.GeneratedFile;
import org.drools.drl.parser.DroolsError;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;

/**
 * Generates rule units, rule unit instances, pojos, queries
 */
public class RuleUnitMainCodegen {
    private final KogitoBuildContext context;
    private final Collection<RuleUnitGenerator> ruleUnitGenerators;
    private final boolean hotReloadMode;
    private final List<QueryGenerator> validQueries;
    private List<DroolsError> errors = new ArrayList<>();

    public RuleUnitMainCodegen(KogitoBuildContext context, Collection<RuleUnitGenerator> ruleUnitGenerators, boolean hotReloadMode) {
        this.context = context;
        this.ruleUnitGenerators = ruleUnitGenerators;
        this.hotReloadMode = hotReloadMode;
        this.validQueries = validateQueries();
    }

    Collection<QueryGenerator> validQueries() {
        return validQueries;
    }

    Collection<DroolsError> errors() {
        return errors;
    }

    Collection<GeneratedFile> generate() {
        List<GeneratedFile> generatedFiles = new ArrayList<>();

        RuleUnitHelper ruleUnitHelper = new RuleUnitHelper(context.getClassLoader(), hotReloadMode);

        for (RuleUnitGenerator ruleUnit : ruleUnitGenerators) {
            ruleUnitHelper.initRuleUnitHelper(ruleUnit.getRuleUnitDescription());
            ruleUnit.pojo(ruleUnitHelper).ifPresent(p -> generatedFiles.add(p.generate()));
        }

        for (QueryGenerator query : validQueries) {
            generatedFiles.add(query.generate());
        }
        return generatedFiles;
    }

    private List<QueryGenerator> validateQueries() {
        List<QueryGenerator> validQueries = new ArrayList<>();
        for (RuleUnitGenerator ruleUnit : ruleUnitGenerators) {
            for (QueryGenerator queryGenerator : ruleUnit.queries()) {
                if (queryGenerator.validate()) {
                    validQueries.add(queryGenerator);
                } else {
                    errors.add(queryGenerator.getError());
                }
            }
        }
        return validQueries;
    }

}
