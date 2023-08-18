package org.jbpm.process.instance.event.listeners;

import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.event.rule.AgendaGroupPoppedEvent;
import org.kie.api.event.rule.AgendaGroupPushedEvent;
import org.kie.api.event.rule.BeforeMatchFiredEvent;
import org.kie.api.event.rule.MatchCancelledEvent;
import org.kie.api.event.rule.MatchCreatedEvent;
import org.kie.api.event.rule.RuleFlowGroupActivatedEvent;
import org.kie.api.event.rule.RuleFlowGroupDeactivatedEvent;
import org.kie.kogito.internal.process.runtime.KogitoProcessRuntime;

/**
 * Dedicated AgendaEventListener that will fireAllRules as soon as:
 * <ul>
 * <li>match is created</li>
 * <li>after rule flow group is activated</li>
 * </ul>
 * This listener should be used to automatically fire rules as soon as they get activated.
 * Especially useful for executing business rule tasks as part of the process.
 */
public class TriggerRulesEventListener implements AgendaEventListener {

    private KogitoProcessRuntime kruntime;

    public TriggerRulesEventListener(KogitoProcessRuntime kruntime) {

        this.kruntime = kruntime;
    }

    @Override
    public void matchCreated(MatchCreatedEvent event) {
    }

    @Override
    public void matchCancelled(MatchCancelledEvent event) {

    }

    @Override
    public void beforeMatchFired(BeforeMatchFiredEvent event) {

    }

    @Override
    public void afterMatchFired(AfterMatchFiredEvent event) {

    }

    @Override
    public void agendaGroupPopped(AgendaGroupPoppedEvent event) {

    }

    @Override
    public void agendaGroupPushed(AgendaGroupPushedEvent event) {

    }

    @Override
    public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {

    }

    @Override
    public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
        kruntime.getKieSession().fireAllRules();

    }

    @Override
    public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {

    }

    @Override
    public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {

    }
}