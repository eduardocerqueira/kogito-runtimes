package org.kie.kogito.integrationtests;

import org.springframework.beans.factory.annotation.Autowired;
import org.kie.kogito.Application;
import org.kie.kogito.rules.RuleUnits;
import org.kie.kogito.rules.RuleConfig;

public class SpringInjectRuleUnits {

    @Autowired
    public SpringInjectRuleUnits(RuleUnits ruleUnits, Application application) {
        if (ruleUnits != application.get(RuleUnits.class)) {
            throw new IllegalStateException("RuleUnits should be injectable and same as instance application.get(RuleUnits.class)");
        }
        if(application.config().get(RuleConfig.class) == null) {
            throw new IllegalStateException("RuleConfig not available");
        }
    }
}