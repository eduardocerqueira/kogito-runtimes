package org.kie.kogito.codegen.rules;

import org.drools.ruleunits.impl.AssignableChecker;
import org.drools.ruleunits.impl.ReflectiveRuleUnitDescription;
import org.kie.internal.ruleunit.RuleUnitDescription;

public class RuleUnitHelper {
    private AssignableChecker defaultChecker;
    private AssignableChecker assignableChecker;

    public RuleUnitHelper() {
    }

    public RuleUnitHelper(ClassLoader cl, boolean hotReloadMode) {
        this.defaultChecker = AssignableChecker.create(cl, hotReloadMode);
    }

    public RuleUnitHelper(AssignableChecker assignableChecker) {
        this.assignableChecker = assignableChecker;
    }

    void initRuleUnitHelper(RuleUnitDescription ruleUnitDesc) {
        if (ruleUnitDesc instanceof ReflectiveRuleUnitDescription) {
            assignableChecker = ((ReflectiveRuleUnitDescription) ruleUnitDesc).getAssignableChecker();
        } else {
            if (assignableChecker == null) {
                assignableChecker = defaultChecker;
            }
        }
    }

    public AssignableChecker getAssignableChecker() {
        return assignableChecker;
    }

    public boolean isAssignableFrom(Class<?> source, Class<?> target) {
        return assignableChecker.isAssignableFrom(source, target);
    }
}
