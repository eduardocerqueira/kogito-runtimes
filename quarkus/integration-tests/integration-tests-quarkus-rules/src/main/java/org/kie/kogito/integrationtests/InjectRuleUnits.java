package org.kie.kogito.integrationtests;

import javax.inject.Inject;

import org.kie.kogito.Application;
import org.kie.kogito.rules.RuleConfig;
import org.kie.kogito.rules.RuleUnits;

import io.quarkus.runtime.Startup;

@Startup
public class InjectRuleUnits {

    @Inject
    public InjectRuleUnits(RuleUnits ruleUnits, Application application) {
        if (ruleUnits != application.get(RuleUnits.class)) {
            throw new IllegalStateException("RuleUnits should be injectable and same as instance application.get(RuleUnits.class)");
        }
        if (application.config().get(RuleConfig.class) == null) {
            throw new IllegalStateException("RuleConfig not available");
        }
    }
}
