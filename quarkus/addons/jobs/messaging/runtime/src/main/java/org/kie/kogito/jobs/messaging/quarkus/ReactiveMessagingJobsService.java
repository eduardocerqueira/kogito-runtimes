package org.kie.kogito.jobs.messaging.quarkus;

import java.net.URI;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import com.fasterxml.jackson.databind.ObjectMapper;

@ApplicationScoped
public class ReactiveMessagingJobsService extends AbstractReactiveMessagingJobsService {

    private static final String KOGITO_ADDON = "jobs-messaging";

    @Inject
    public ReactiveMessagingJobsService(
            @ConfigProperty(name = "kogito.service.url") URI serviceUrl,
            ObjectMapper objectMapper,
            @Channel(KOGITO_JOB_SERVICE_JOB_REQUEST_EVENTS_CHANNEL) Emitter<String> eventsEmitter) {
        super(serviceUrl, objectMapper, eventsEmitter);
    }

    @Override
    protected String getAddonName() {
        return KOGITO_ADDON;
    }
}
