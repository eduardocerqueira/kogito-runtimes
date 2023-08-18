package org.kie.kogito.event.impl;

import org.kie.kogito.event.DataEventFactory;
import org.kie.kogito.event.EventEmitter;
import org.kie.kogito.internal.process.runtime.KogitoProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractMessageProducer<D> implements MessageProducer<D> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractMessageProducer.class);

    private String trigger;
    private EventEmitter emitter;

    // in general, we should favor the non-empty constructor
    // but there is an issue with Quarkus https://github.com/quarkusio/quarkus/issues/2949#issuecomment-513017781
    // use this in conjunction with setParams()
    public AbstractMessageProducer() {
    }

    public AbstractMessageProducer(EventEmitter emitter, String trigger) {
        init(emitter, trigger);
    }

    protected void init(EventEmitter emitter, String trigger) {
        this.emitter = emitter;
        this.trigger = trigger;

    }

    @Override
    public void produce(KogitoProcessInstance pi, D eventData) {
        emitter.emit(DataEventFactory.from(eventData, trigger, pi))
                .exceptionally(ex -> {
                    logger.error("An error was caught while process " + pi.getProcessId() + " produced message " + eventData, ex);
                    return null;
                });
    }
}
