package org.kie.kogito.event;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

import org.kie.kogito.event.cloudevents.extension.ProcessMeta;
import org.kie.kogito.event.impl.CloudEventWrapDataEvent;
import org.kie.kogito.event.process.ProcessDataEvent;
import org.kie.kogito.internal.process.runtime.KogitoProcessInstance;

import io.cloudevents.CloudEvent;
import io.cloudevents.CloudEventData;
import io.cloudevents.CloudEventExtension;
import io.cloudevents.SpecVersion;

public class DataEventFactory {

    public static <T> DataEvent<T> from(T event) {
        return new ProcessDataEvent<>(event);
    }

    public static <T> DataEvent<T> from(CloudEvent event, Converter<CloudEventData, T> dataUnmarshaller) {
        return new CloudEventWrapDataEvent<>(event, dataUnmarshaller);
    }

    public static <T> DataEvent<T> from(T eventData, String trigger, KogitoProcessInstance pi) {
        return from(eventData, trigger, URI.create("/process/" + pi.getProcessId()), Optional.empty(), ProcessMeta.fromKogitoProcessInstance(pi));
    }

    public static <T> DataEvent<T> from(T eventData, String type, URI source, Optional<String> subject, CloudEventExtension... extensions) {
        ProcessDataEvent<T> ce = new ProcessDataEvent<>(eventData);
        ce.setSpecVersion(SpecVersion.V1);
        ce.setId(UUID.randomUUID().toString());
        ce.setType(type);
        ce.setSource(source);
        ce.setTime(OffsetDateTime.now());
        subject.ifPresent(ce::setSubject);
        for (CloudEventExtension extension : extensions) {
            for (String key : extension.getKeys()) {
                ce.addExtensionAttribute(key, extension.getValue(key));
            }
        }
        return ce;
    }

    private DataEventFactory() {
    }

}
