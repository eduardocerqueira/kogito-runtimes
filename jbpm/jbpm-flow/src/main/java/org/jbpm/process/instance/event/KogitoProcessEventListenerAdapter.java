package org.jbpm.process.instance.event;

import org.kie.api.event.process.MessageEvent;
import org.kie.api.event.process.ProcessCompletedEvent;
import org.kie.api.event.process.ProcessEventListener;
import org.kie.api.event.process.ProcessNodeLeftEvent;
import org.kie.api.event.process.ProcessNodeTriggeredEvent;
import org.kie.api.event.process.ProcessStartedEvent;
import org.kie.api.event.process.ProcessVariableChangedEvent;
import org.kie.api.event.process.SLAViolatedEvent;
import org.kie.api.event.process.SignalEvent;
import org.kie.kogito.internal.process.event.KogitoProcessEventListener;

public class KogitoProcessEventListenerAdapter implements KogitoProcessEventListener {

    private final ProcessEventListener delegate;

    public KogitoProcessEventListenerAdapter(ProcessEventListener delegate) {
        this.delegate = delegate;
    }

    @Override
    public void beforeProcessStarted(ProcessStartedEvent processStartedEvent) {
        delegate.beforeProcessStarted(processStartedEvent);
    }

    @Override
    public void afterProcessStarted(ProcessStartedEvent processStartedEvent) {
        delegate.afterProcessStarted(processStartedEvent);
    }

    @Override
    public void beforeProcessCompleted(ProcessCompletedEvent processCompletedEvent) {
        delegate.beforeProcessCompleted(processCompletedEvent);
    }

    @Override
    public void afterProcessCompleted(ProcessCompletedEvent processCompletedEvent) {
        delegate.afterProcessCompleted(processCompletedEvent);
    }

    @Override
    public void beforeNodeTriggered(ProcessNodeTriggeredEvent processNodeTriggeredEvent) {
        delegate.beforeNodeTriggered(processNodeTriggeredEvent);
    }

    @Override
    public void afterNodeTriggered(ProcessNodeTriggeredEvent processNodeTriggeredEvent) {
        delegate.afterNodeTriggered(processNodeTriggeredEvent);
    }

    @Override
    public void beforeNodeLeft(ProcessNodeLeftEvent processNodeLeftEvent) {
        delegate.beforeNodeLeft(processNodeLeftEvent);
    }

    @Override
    public void afterNodeLeft(ProcessNodeLeftEvent processNodeLeftEvent) {
        delegate.afterNodeLeft(processNodeLeftEvent);
    }

    @Override
    public void beforeVariableChanged(ProcessVariableChangedEvent processVariableChangedEvent) {
        delegate.beforeVariableChanged(processVariableChangedEvent);
    }

    @Override
    public void afterVariableChanged(ProcessVariableChangedEvent processVariableChangedEvent) {
        delegate.afterVariableChanged(processVariableChangedEvent);
    }

    @Override
    public void beforeSLAViolated(SLAViolatedEvent event) {
        delegate.beforeSLAViolated(event);
    }

    @Override
    public void afterSLAViolated(SLAViolatedEvent event) {
        delegate.afterSLAViolated(event);
    }

    @Override
    public void onSignal(SignalEvent event) {
        delegate.onSignal(event);
    }

    @Override
    public void onMessage(MessageEvent event) {
        delegate.onMessage(event);
    }
}