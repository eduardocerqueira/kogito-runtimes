package org.kie.kogito.jobs.messaging.quarkus;

import java.net.URI;

import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Metadata;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

class ReactiveMessagingJobsServiceTest extends AbstractReactiveMessagingJobsServiceTest<ReactiveMessagingJobsService> {

    @Override
    protected ReactiveMessagingJobsService createJobsService(URI serviceUrl, ObjectMapper objectMapper, Emitter<String> eventsEmitter) {
        return new ReactiveMessagingJobsService(serviceUrl, objectMapper, eventsEmitter);
    }

    @Override
    protected void verifyEmitterWasInvoked(int times, String... expectedPayloads) {
        super.verifyEmitterWasInvoked(times, expectedPayloads);
        for (int i = 0; i < times; i++) {
            Message<String> message = messageCaptor.getAllValues().get(i);
            assertThat(message.getMetadata()).isEqualTo(Metadata.empty());
        }
    }
}
