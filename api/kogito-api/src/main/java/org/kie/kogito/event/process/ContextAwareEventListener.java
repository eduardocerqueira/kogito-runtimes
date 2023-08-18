package org.kie.kogito.event.process;

import java.util.function.Consumer;

import org.kie.api.event.process.DefaultProcessEventListener;
import org.kie.api.event.process.ProcessEventListener;
import org.kie.api.event.process.ProcessNodeLeftEvent;
import org.kie.api.event.process.ProcessVariableChangedEvent;

public class ContextAwareEventListener extends DefaultProcessEventListener {

    private final Consumer<ContextAwareEventListener> action;

    private ContextAwareEventListener(Consumer<ContextAwareEventListener> action) {
        this.action = action;
    }

    @Override
    public void afterNodeLeft(ProcessNodeLeftEvent event) {
        action.accept(this);
    }

    @Override
    public void afterVariableChanged(ProcessVariableChangedEvent event) {
        action.accept(this);
    }

    public static ProcessEventListener using(Consumer<ContextAwareEventListener> action) {
        return new ContextAwareEventListener(action);
    }
}