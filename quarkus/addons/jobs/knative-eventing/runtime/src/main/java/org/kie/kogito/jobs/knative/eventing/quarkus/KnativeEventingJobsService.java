package org.kie.kogito.jobs.knative.eventing.quarkus;

import java.net.URI;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.kie.kogito.addon.quarkus.messaging.common.message.CloudEventHttpOutgoingDecorator;
import org.kie.kogito.jobs.messaging.quarkus.AbstractReactiveMessagingJobsService;

import com.fasterxml.jackson.databind.ObjectMapper;

@ApplicationScoped
public class KnativeEventingJobsService extends AbstractReactiveMessagingJobsService {

    private static final String KOGITO_ADDON = "jobs-knative-eventing";

    private static final CloudEventHttpOutgoingDecorator HTTP_OUTGOING_DECORATOR = new CloudEventHttpOutgoingDecorator();

    @Inject
    public KnativeEventingJobsService(
            @ConfigProperty(name = "kogito.service.url") URI serviceUrl,
            ObjectMapper objectMapper,
            @Channel(KOGITO_JOB_SERVICE_JOB_REQUEST_EVENTS_CHANNEL) Emitter<String> eventsEmitter) {
        super(serviceUrl, objectMapper, eventsEmitter);
    }

    @Override
    protected Message<String> decorate(Message<String> message) {
        return HTTP_OUTGOING_DECORATOR.decorate(message);
    }

    @Override
    protected String getAddonName() {
        return KOGITO_ADDON;
    }
}
