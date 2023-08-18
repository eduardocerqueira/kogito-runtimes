package org.kie.kogito.incubation.rules;

import org.kie.kogito.incubation.common.ComponentRoot;

public class RuleUnitIds implements ComponentRoot {
    public RuleUnitId get(Class<?> ruleUnitDefinition) {
        return get(ruleUnitDefinition.getCanonicalName());
    }

    public RuleUnitId get(String canonicalName) {
        return new RuleUnitId(canonicalName);
    }
}
