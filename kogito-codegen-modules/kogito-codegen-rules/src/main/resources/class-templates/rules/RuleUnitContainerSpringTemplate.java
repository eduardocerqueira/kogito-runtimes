package $Package$;

import org.drools.core.common.ReteEvaluator;
import org.drools.ruleunits.api.RuleUnit;
import org.drools.ruleunits.api.RuleUnitData;
import org.drools.ruleunits.impl.InternalRuleUnit;
import org.drools.ruleunits.impl.sessions.RuleUnitExecutorImpl;
import org.kie.kogito.rules.RuleEventListenerConfig;

@org.springframework.web.context.annotation.ApplicationScope
@org.springframework.stereotype.Component
public class RuleUnits extends org.kie.kogito.drools.core.unit.AbstractRuleUnits implements org.kie.kogito.rules.RuleUnits {

    @org.springframework.beans.factory.annotation.Autowired
    Application application;

    @Override
    protected <T extends RuleUnitData> RuleUnit<T> internalCreate(Class<T> clazz) {
        String fqcn = clazz.getCanonicalName();
        switch(fqcn) {
            case "$RuleUnit$":
                return (RuleUnit<T>) new $RuleUnit$RuleUnit(this);
            default:
                throw new java.lang.UnsupportedOperationException();
        }
    }

    @Override
    public void register(RuleUnit<?> unit) {
        registerRuleUnit(application, (InternalRuleUnit<?>) unit);
    }
}
