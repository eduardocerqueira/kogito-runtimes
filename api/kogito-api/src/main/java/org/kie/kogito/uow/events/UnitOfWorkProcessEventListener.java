package org.kie.kogito.uow.events;

import org.kie.api.event.process.MessageEvent;
import org.kie.api.event.process.ProcessCompletedEvent;
import org.kie.api.event.process.ProcessEvent;
import org.kie.api.event.process.ProcessNodeLeftEvent;
import org.kie.api.event.process.ProcessNodeTriggeredEvent;
import org.kie.api.event.process.ProcessStartedEvent;
import org.kie.api.event.process.ProcessVariableChangedEvent;
import org.kie.api.event.process.SLAViolatedEvent;
import org.kie.api.event.process.SignalEvent;
import org.kie.kogito.internal.process.event.DefaultKogitoProcessEventListener;
import org.kie.kogito.internal.process.event.HumanTaskDeadlineEvent;
import org.kie.kogito.internal.process.event.ProcessWorkItemTransitionEvent;
import org.kie.kogito.uow.UnitOfWorkManager;
import org.kie.kogito.uow.WorkUnit;

public class UnitOfWorkProcessEventListener extends DefaultKogitoProcessEventListener {

    UnitOfWorkManager unitOfWorkManager;

    public UnitOfWorkProcessEventListener(UnitOfWorkManager unitOfWorkManager) {
        this.unitOfWorkManager = unitOfWorkManager;
    }

    private void intercept(ProcessEvent event) {
        unitOfWorkManager.currentUnitOfWork().intercept(WorkUnit.create(event, e -> {
        }));
    }

    @Override
    public void beforeProcessStarted(ProcessStartedEvent event) {
        intercept(event);
    }

    @Override
    public void afterProcessStarted(ProcessStartedEvent event) {
        intercept(event);
    }

    @Override
    public void beforeProcessCompleted(ProcessCompletedEvent event) {
        intercept(event);
    }

    @Override
    public void afterProcessCompleted(ProcessCompletedEvent event) {
        intercept(event);
    }

    @Override
    public void beforeNodeTriggered(ProcessNodeTriggeredEvent event) {
        intercept(event);
    }

    @Override
    public void afterNodeTriggered(ProcessNodeTriggeredEvent event) {
        intercept(event);
    }

    @Override
    public void beforeNodeLeft(ProcessNodeLeftEvent event) {
        intercept(event);
    }

    @Override
    public void afterNodeLeft(ProcessNodeLeftEvent event) {
        intercept(event);
    }

    @Override
    public void beforeVariableChanged(ProcessVariableChangedEvent event) {
        intercept(event);
    }

    @Override
    public void afterVariableChanged(ProcessVariableChangedEvent event) {
        intercept(event);
    }

    @Override
    public void beforeSLAViolated(SLAViolatedEvent event) {
        intercept(event);
    }

    @Override
    public void afterSLAViolated(SLAViolatedEvent event) {
        intercept(event);
    }

    @Override
    public void beforeWorkItemTransition(ProcessWorkItemTransitionEvent event) {
        intercept(event);
    }

    @Override
    public void afterWorkItemTransition(ProcessWorkItemTransitionEvent event) {
        intercept(event);
    }

    @Override
    public void onSignal(SignalEvent event) {
        intercept(event);
    }

    @Override
    public void onMessage(MessageEvent event) {
        intercept(event);
    }

    @Override
    public void onHumanTaskDeadline(HumanTaskDeadlineEvent event) {
        intercept(event);
    }

}
