package org.kie.kogito.jobs.knative.eventing.quarkus;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.HttpHeaders;

import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.kie.kogito.jobs.messaging.quarkus.AbstractReactiveMessagingJobsServiceTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.quarkus.reactivemessaging.http.runtime.OutgoingHttpMetadata;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.kie.kogito.addon.quarkus.messaging.common.message.CloudEventHttpOutgoingDecorator.CLOUD_EVENTS_CONTENT_TYPE;

class KnativeEventingJobsServiceTest extends AbstractReactiveMessagingJobsServiceTest<KnativeEventingJobsService> {

    @Override
    protected KnativeEventingJobsService createJobsService(URI serviceUrl, ObjectMapper objectMapper, Emitter<String> eventsEmitter) {
        return new KnativeEventingJobsService(serviceUrl, objectMapper, eventsEmitter);
    }

    @Override
    protected void verifyEmitterWasInvoked(int times, String... expectedPayloads) {
        super.verifyEmitterWasInvoked(times, expectedPayloads);
        for (int i = 0; i < times; i++) {
            Message<String> message = messageCaptor.getAllValues().get(i);
            assertHasExpectedHttpMetadata(message);
        }
    }

    private void assertHasExpectedHttpMetadata(Message<String> message) {
        Optional<OutgoingHttpMetadata> httpMetadata = message.getMetadata(OutgoingHttpMetadata.class);
        if (httpMetadata.isEmpty()) {
            fail("Message doesn't have the expected OutgoingHttpMetadata");
        } else {
            assertThat(httpMetadata.get().getHeaders()).hasSize(1);
            List<String> contentTypeValues = httpMetadata.get().getHeaders().get(HttpHeaders.CONTENT_TYPE);
            assertThat(contentTypeValues).containsExactly(CLOUD_EVENTS_CONTENT_TYPE);
        }
    }
}
