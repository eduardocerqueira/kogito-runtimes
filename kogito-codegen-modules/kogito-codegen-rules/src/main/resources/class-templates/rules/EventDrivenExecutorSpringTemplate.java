package com.myspace.demo;

import org.kie.kogito.eventdriven.rules.AbstractEventDrivenQueryExecutor;
import org.drools.ruleunits.api.RuleUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.kie.kogito.eventdriven.rules.EventDrivenRulesController;
@Component
public class $QueryType$EventDrivenExecutor extends AbstractEventDrivenQueryExecutor<$DataType$> {

    @Autowired
    public $QueryType$EventDrivenExecutor(EventDrivenRulesController controller, RuleUnit<$DataType$> ruleUnit) {
        super(controller, ruleUnit, "$name$", $QueryType$::execute, $DataType$.class);
    }
}
