package org.kie.kogito.quarkus.workflow.devservices;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.kie.kogito.event.DataEvent;
import org.kie.kogito.event.EventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.predicate.ResponsePredicate;

public class DataIndexEventPublisher implements EventPublisher {

    public static final String KOGITO_DATA_INDEX = "kogito.data-index.url";
    private static final Logger LOGGER = LoggerFactory.getLogger(DataIndexEventPublisher.class);

    @ConfigProperty(name = KOGITO_DATA_INDEX)
    Optional<String> dataIndexUrl;

    @Inject
    Vertx vertx;

    WebClient webClient;

    @PostConstruct
    public void init() {
        webClient = WebClient.create(vertx);
    }

    @Override
    public void publish(DataEvent<?> event) {
        if (dataIndexUrl.isEmpty()) {
            return;
        }

        LOGGER.debug("Sending event to data index: {}", event);
        switch (event.getType()) {
            case "ProcessInstanceEvent":
                webClient.postAbs(dataIndexUrl.get() + "/processes")
                        .expect(ResponsePredicate.SC_ACCEPTED)
                        .sendJson(event, result -> {
                            if (result.failed()) {
                                LOGGER.error("Failed to send message to Data Index", result.cause());
                            } else {
                                LOGGER.debug("Event published to Data Index");
                            }
                        });
                break;
            case "UserTaskInstanceEvent":
                webClient.postAbs(dataIndexUrl.get() + "/tasks")
                        .expect(ResponsePredicate.SC_ACCEPTED)
                        .sendJson(event, result -> {
                            if (result.failed()) {
                                LOGGER.error("Failed to send message to Data Index", result.cause());
                            } else {
                                LOGGER.debug("Event published to Data Index");
                            }
                        });
                break;
            default:
                LOGGER.debug("Unknown type of event '{}', ignoring for this publisher", event.getType());
        }
    }

    @Override
    public void publish(Collection<DataEvent<?>> events) {
        events.forEach(this::publish);
    }

}
