package org.kie.kogito.codegen.rules;

import org.kie.kogito.codegen.AbstractCodegenIT;
import org.kie.kogito.codegen.decision.DecisionCodegen;

public abstract class AbstractRulesCodegenIT extends AbstractCodegenIT {

    static {
        generatorTypeMap.put(TYPE.RULES, (context, strings) -> RuleCodegen.ofCollectedResources(context, toCollectedResources(TEST_RESOURCES, strings)));
        generatorTypeMap.put(TYPE.DECISION, (context, strings) -> DecisionCodegen.ofCollectedResources(context, toCollectedResources(TEST_RESOURCES, strings)));
        generatorTypeMap.put(TYPE.JAVA, (context, strings) -> RuleCodegen.ofJavaResources(context, toCollectedResources(TEST_JAVA, strings)));
    }

}
