package org.jbpm.workflow.core.node;

import org.drools.ruleunits.api.RuleUnit;
import org.drools.ruleunits.api.RuleUnitData;
import org.kie.api.runtime.process.ProcessContext;

public interface RuleUnitFactory<T extends RuleUnitData> {

    T bind(ProcessContext ctx);

    RuleUnit<T> unit();

    void unbind(ProcessContext ctx, T model);
}
