package org.kie.kogito.event;

import java.util.function.Function;

import io.cloudevents.CloudEvent;
import io.cloudevents.CloudEventContext;
import io.cloudevents.CloudEventData;

/**
 * Represents top level data event structure that can be emitted
 * from within running process, decision or rule.
 * <p>
 * It's main aim is to be transferred over the wire but the event
 * itself is not meant to do transformation to be "wire-friendly"
 * <p>
 * Main point of the event is to be compatible with cloud events
 * specification and thus comes with main fields that the spec defines.
 * <p>
 * Classes implementing can provide more information to be considered extensions
 * of the event - see cloud event extension elements.
 *
 * @param <T> type of the body of the event
 */
public interface DataEvent<T> extends CloudEventContext {
    /**
     * Returns actual body of the event
     *
     * @return
     */
    T getData();

    String getKogitoProcessInstanceId();

    String getKogitoRootProcessInstanceId();

    String getKogitoProcessId();

    String getKogitoRootProcessId();

    String getKogitoAddons();

    String getKogitoParentProcessInstanceId();

    String getKogitoProcessInstanceState();

    String getKogitoReferenceId();

    String getKogitoBusinessKey();

    String getKogitoStartFromNode();

    String getKogitoProcessInstanceVersion();

    String getKogitoProcessType();

    String getKogitoIdentity();

    CloudEvent asCloudEvent(Function<T, CloudEventData> converter);
}
