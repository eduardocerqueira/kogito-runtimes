package com.myspace.demo;

import java.io.IOException;
import java.util.Optional;

import org.kie.kogito.event.EventMarshaller;
import org.kie.kogito.event.impl.StringEventMarshaller;
import org.kie.kogito.event.process.ProcessDataEvent;
import org.kie.kogito.internal.process.runtime.KogitoProcessInstance;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MessageProducer extends org.kie.kogito.event.impl.AbstractMessageProducer<java.lang.String>{


    Optional<Boolean> useCloudEvents = Optional.of(true);

    EventMarshaller<String> marshaller = new StringEventMarshaller(new ObjectMapper());

    public void configure() {

    }

    public void produce(KogitoProcessInstance pi, $Type$ eventData) {

    }

    private String marshall(KogitoProcessInstance pi, $Type$ eventData) throws IOException {
        return marshaller.marshall(useCloudEvents.orElse(true) ? new ProcessDataEvent<>(
                "",
                "",
                eventData,
                pi.getStringId(),
                pi.getProcess().getVersion(),
                pi.getParentProcessInstanceId(),
                pi.getRootProcessInstanceId(),
                pi.getProcessId(),
                pi.getRootProcessId(),
                String.valueOf(pi.getState()),
                null,
                pi.getProcess().getType(),
                pi.getReferenceId() == null || pi.getReferenceId().trim().isEmpty() ? null : pi.getReferenceId(),
                null) : eventData);
    }
}