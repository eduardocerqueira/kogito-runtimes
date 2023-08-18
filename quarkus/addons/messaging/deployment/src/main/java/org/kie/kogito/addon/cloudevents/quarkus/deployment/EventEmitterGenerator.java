package org.kie.kogito.addon.cloudevents.quarkus.deployment;

import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.event.CloudEventMarshaller;
import org.kie.kogito.event.EventMarshaller;

public class EventEmitterGenerator extends EventGenerator {

    public EventEmitterGenerator(KogitoBuildContext context, ChannelInfo channelInfo) {
        super(context, channelInfo, "EventEmitter");
        if (context.getApplicationProperty("kogito.messaging.as-cloudevents", Boolean.class).orElse(true)) {
            generateMarshallerField("marshaller", "setCloudEventMarshaller", CloudEventMarshaller.class);
        } else {
            generateMarshallerField("marshaller", "setEventDataMarshaller", EventMarshaller.class);
        }
    }
}
