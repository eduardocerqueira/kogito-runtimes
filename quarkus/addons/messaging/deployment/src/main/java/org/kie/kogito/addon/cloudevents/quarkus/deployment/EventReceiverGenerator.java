package org.kie.kogito.addon.cloudevents.quarkus.deployment;

import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.event.CloudEventUnmarshallerFactory;
import org.kie.kogito.event.EventUnmarshaller;

public class EventReceiverGenerator extends EventGenerator {

    public EventReceiverGenerator(KogitoBuildContext context, ChannelInfo channelInfo) {
        super(context, channelInfo, "EventReceiver");
        if (context.getApplicationProperty("kogito.messaging.as-cloudevents", Boolean.class).orElse(true)) {
            generateMarshallerField("unmarshaller", "setCloudEventUnmarshaller", CloudEventUnmarshallerFactory.class);
        } else {
            generateMarshallerField("unmarshaller", "setEventDataUnmarshaller", EventUnmarshaller.class);
        }
    }

}
