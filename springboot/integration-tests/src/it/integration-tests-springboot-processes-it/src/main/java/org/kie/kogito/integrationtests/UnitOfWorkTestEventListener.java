package org.kie.kogito.integrationtests;

import java.util.ArrayList;
import java.util.List;

import org.kie.kogito.uow.events.UnitOfWorkAbortEvent;
import org.kie.kogito.uow.events.UnitOfWorkEndEvent;
import org.kie.kogito.uow.events.UnitOfWorkEventListener;
import org.kie.kogito.uow.events.UnitOfWorkStartEvent;

import org.springframework.stereotype.Component;

@Component
public class UnitOfWorkTestEventListener implements UnitOfWorkEventListener {

    private List<UnitOfWorkStartEvent> startEvents = new ArrayList<>();
    private List<UnitOfWorkEndEvent> endEvents = new ArrayList<>();
    private List<UnitOfWorkAbortEvent> abortEvents = new ArrayList<>();

    @Override
    public void onBeforeStartEvent(UnitOfWorkStartEvent event) {
        this.startEvents.add(event);
    }

    @Override
    public void onAfterEndEvent(UnitOfWorkEndEvent event) {
        this.endEvents.add(event);
    }

    @Override
    public void onAfterAbortEvent(UnitOfWorkAbortEvent event) {
        this.abortEvents.add(event);
    }

    public void reset() {
        startEvents.clear();
        endEvents.clear();
        abortEvents.clear();
    }

    public List<UnitOfWorkStartEvent> getStartEvents() {
        return startEvents;
    }

    public List<UnitOfWorkEndEvent> getEndEvents() {
        return endEvents;
    }

    public List<UnitOfWorkAbortEvent> getAbortEvents() {
        return abortEvents;
    }
}