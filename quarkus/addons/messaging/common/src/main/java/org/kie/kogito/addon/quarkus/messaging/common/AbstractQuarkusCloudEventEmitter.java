package org.kie.kogito.addon.quarkus.messaging.common;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Message;
import org.kie.kogito.addon.quarkus.common.reactive.messaging.MessageDecoratorProvider;
import org.kie.kogito.event.CloudEventMarshaller;
import org.kie.kogito.event.DataEvent;
import org.kie.kogito.event.EventEmitter;
import org.kie.kogito.event.EventMarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractQuarkusCloudEventEmitter<M> implements EventEmitter {

    private static final Logger logger = LoggerFactory.getLogger(AbstractQuarkusCloudEventEmitter.class);

    @Inject
    MessageDecoratorProvider messageDecorator;

    private CloudEventMarshaller<M> cloudEventMarshaller;

    private EventMarshaller<M> eventMarshaller;

    @Override
    public CompletionStage<Void> emit(DataEvent<?> dataEvent) {
        logger.debug("publishing event {}", dataEvent);
        try {
            Message<M> message = messageDecorator.decorate(Message.of(getPayload(dataEvent))
                    .withNack(e -> {
                        logger.error("Error publishing event {}", dataEvent, e);
                        return CompletableFuture.completedFuture(null);
                    }));
            emit(message);
            return message.getAck().get();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    protected void setEventDataMarshaller(EventMarshaller<M> marshaller) {
        this.eventMarshaller = marshaller;
    }

    protected void setCloudEventMarshaller(CloudEventMarshaller<M> marshaller) {
        this.cloudEventMarshaller = marshaller;
    }

    private <T> M getPayload(DataEvent<T> event) throws IOException {
        if (cloudEventMarshaller != null) {
            return cloudEventMarshaller.marshall(event.asCloudEvent(cloudEventMarshaller.cloudEventDataFactory()));
        } else if (eventMarshaller != null) {
            return eventMarshaller.marshall(event.getData());
        } else {
            throw new IllegalStateException("Not marshaller has been set for emitter " + this);
        }
    }

    protected abstract void emit(Message<M> message);
}
