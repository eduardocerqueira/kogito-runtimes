package org.kie.kogito.addon.quarkus.messaging.common.message;

import java.util.Collections;
import java.util.Optional;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Message;
import org.junit.jupiter.api.Test;
import org.kie.kogito.KogitoGAV;
import org.kie.kogito.addon.quarkus.common.reactive.messaging.MessageDecoratorProvider;
import org.kie.kogito.config.ConfigBean;

import io.quarkus.reactivemessaging.http.runtime.OutgoingHttpMetadata;
import io.quarkus.test.junit.QuarkusTest;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
class CloudEventHttpOutgoingDecoratorTest {

    @Inject
    MessageDecoratorProvider provider;

    @Produces
    CloudEventHttpOutgoingDecorator decorator = new CloudEventHttpOutgoingDecorator();

    @Produces
    ConfigBean configBean = new ConfigBean() {

        @Override
        public boolean useCloudEvents() {
            return true;
        }

        @Override
        public String getServiceUrl() {
            return null;
        }

        @Override
        public Optional<KogitoGAV> getGav() {
            return Optional.empty();
        }
    };

    @Test
    void verifyOutgoingHttpMetadataIsSet() {
        Message<String> message = provider.decorate(Message.of("pepe"));
        Optional<OutgoingHttpMetadata> metadata = message.getMetadata(OutgoingHttpMetadata.class);
        assertThat(metadata).isNotEmpty();
        assertThat(metadata.orElseThrow().getHeaders()).containsEntry("Content-Type", Collections.singletonList("application/cloudevents+json"));
    }
}
