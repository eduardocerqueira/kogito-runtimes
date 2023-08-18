package org.kie.kogito.test;

import org.kie.kogito.event.EventEmitter;
import org.kie.kogito.event.impl.AbstractMessageProducer;

@org.springframework.stereotype.Component()
public class MessageProducer extends AbstractMessageProducer<$DataType$> {

    @org.springframework.beans.factory.annotation.Autowired()
    MessageProducer(EventEmitter emitter) {
        super(emitter,"$Trigger$");
    }
}
