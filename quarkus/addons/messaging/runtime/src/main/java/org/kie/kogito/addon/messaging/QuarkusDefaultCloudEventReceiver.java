package org.kie.kogito.addon.messaging;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.kie.kogito.addon.quarkus.messaging.common.AbstractQuarkusCloudEventReceiver;
import org.kie.kogito.config.ConfigBean;
import org.kie.kogito.event.CloudEventUnmarshallerFactory;
import org.kie.kogito.event.EventUnmarshaller;

import io.quarkus.arc.DefaultBean;

@DefaultBean
@ApplicationScoped
public class QuarkusDefaultCloudEventReceiver extends AbstractQuarkusCloudEventReceiver<Object> {

    @Inject
    ConfigBean configBean;

    @Inject
    CloudEventUnmarshallerFactory<Object> cloudEventUnmarshaller;

    @Inject
    EventUnmarshaller<Object> eventUnmarshaller;

    @PostConstruct
    void init() {
        if (configBean.useCloudEvents()) {
            setCloudEventUnmarshaller(cloudEventUnmarshaller);
        } else {
            setEventDataUnmarshaller(eventUnmarshaller);
        }
    }
}
