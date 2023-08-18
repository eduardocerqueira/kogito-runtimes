package org.jbpm.workflow.instance.impl;

import org.kie.kogito.internal.process.event.KogitoEventListener;

public class DummyEventListener implements KogitoEventListener {

    public static final DummyEventListener EMPTY_EVENT_LISTENER = new DummyEventListener();

    private DummyEventListener() {
    }

    @Override
    public void signalEvent(String type, Object event) {
    }

    @Override
    public String[] getEventTypes() {
        return new String[0];
    }

}
