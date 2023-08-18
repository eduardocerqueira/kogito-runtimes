package org.kie.kogito.test;

import org.kie.kogito.internal.process.runtime.KogitoProcessInstance;
import org.kie.kogito.event.EventEmitter;
import org.kie.kogito.event.impl.AbstractMessageProducer;
import javax.inject.Inject;

@io.quarkus.runtime.Startup
public class MessageProducer extends AbstractMessageProducer<$DataType$> {

    @Inject
    EventEmitter emitter;

    @javax.annotation.PostConstruct
    public void init() {
        init(emitter,"$Trigger$");
    }
}
