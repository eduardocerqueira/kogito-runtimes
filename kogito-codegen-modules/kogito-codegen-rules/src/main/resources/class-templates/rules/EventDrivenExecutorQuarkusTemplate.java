package com.myspace.demo;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.kie.kogito.eventdriven.rules.AbstractEventDrivenQueryExecutor;
import org.drools.ruleunits.api.RuleUnit;
import org.kie.kogito.eventdriven.rules.EventDrivenRulesController;
import io.quarkus.runtime.Startup;


@ApplicationScoped
@Startup
public class $QueryType$EventDrivenExecutor extends AbstractEventDrivenQueryExecutor<$DataType$> {

    @Inject
    RuleUnit<$DataType$> ruleUnit;
    
    @Inject
    EventDrivenRulesController controller;

    @PostConstruct
    private void onPostConstruct() {
        setup(controller, ruleUnit, "$name$", $QueryType$::execute, $DataType$.class);
    }
}
