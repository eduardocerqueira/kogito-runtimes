package org.kie.kogito.event.impl;

import org.kie.kogito.internal.process.runtime.KogitoProcessInstance;

public interface MessageProducer<D> {
    void produce(KogitoProcessInstance pi, D eventData);
}
