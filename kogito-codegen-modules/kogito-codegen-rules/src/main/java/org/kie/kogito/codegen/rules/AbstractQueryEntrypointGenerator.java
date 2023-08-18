package org.kie.kogito.codegen.rules;

import org.kie.internal.ruleunit.RuleUnitDescription;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.codegen.api.context.impl.JavaKogitoBuildContext;
import org.kie.kogito.codegen.api.template.TemplatedGenerator;

import static org.kie.kogito.codegen.rules.RuleCodegen.TEMPLATE_RULE_FOLDER;

public abstract class AbstractQueryEntrypointGenerator implements RuleFileGenerator {

    protected final RuleUnitDescription ruleUnit;
    protected final QueryGenerator query;
    protected final KogitoBuildContext context;

    protected final String queryName;
    protected final String queryClassName;
    protected final String targetClassName;
    protected final TemplatedGenerator generator;

    protected AbstractQueryEntrypointGenerator(
            QueryGenerator query,
            String targetClassNameSuffix,
            String templateName) {
        this.ruleUnit = query.ruleUnit();
        this.query = query;
        this.context = query.context();

        this.queryName = query.name();
        this.queryClassName = ruleUnit.getSimpleName() + "Query" + queryName;
        this.targetClassName = queryClassName + targetClassNameSuffix;

        this.generator = TemplatedGenerator.builder()
                .withPackageName(query.model().getNamespace())
                .withTemplateBasePath(TEMPLATE_RULE_FOLDER)
                .withTargetTypeName(targetClassName)
                .withFallbackContext(JavaKogitoBuildContext.CONTEXT_NAME)
                .build(context, templateName);
    }

    @Override
    public String generatedFilePath() {
        return generator.generatedFilePath();
    }

}
