package org.jbpm.process.core.event;

import org.kie.kogito.event.EventEmitter;
import org.kie.kogito.event.impl.AbstractMessageProducer;
import org.kie.kogito.event.impl.EventFactoryUtils;

public class StaticMessageProducer<D> extends AbstractMessageProducer<D> {

    public StaticMessageProducer(String trigger) {
        this(trigger, EventFactoryUtils.getEventEmitter(trigger));
    }

    public StaticMessageProducer(String trigger, EventEmitter emitter) {
        super(emitter, trigger);
    }
}
