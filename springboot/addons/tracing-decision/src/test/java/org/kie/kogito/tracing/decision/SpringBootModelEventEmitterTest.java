package org.kie.kogito.tracing.decision;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.kie.kogito.KogitoGAV;
import org.kie.kogito.decision.DecisionModelMetadata;
import org.kie.kogito.decision.DecisionModelResource;
import org.kie.kogito.decision.DecisionModelResourcesProvider;
import org.mockito.ArgumentCaptor;
import org.springframework.kafka.core.KafkaTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cloudevents.CloudEvent;
import io.cloudevents.jackson.JsonFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SpringBootModelEventEmitterTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().registerModule(JsonFormat.getCloudEventJacksonModule());
    private static final String TEST_TOPIC = "test-topic";

    @Test
    public void testEmitEvent() throws JsonProcessingException {
        @SuppressWarnings("unchecked")
        final KafkaTemplate<String, String> mockedKarkaTemplate = mock(KafkaTemplate.class);
        final List<DecisionModelResource> models = Arrays.asList(makeModel(), makeModel());
        final DecisionModelResourcesProvider mockedDecisionModelResourcesProvider = () -> models;

        final SpringBootModelEventEmitter eventEmitter = new SpringBootModelEventEmitter(mockedDecisionModelResourcesProvider, mockedKarkaTemplate, TEST_TOPIC);
        eventEmitter.publishDecisionModels();

        final ArgumentCaptor<String> topicCaptor = ArgumentCaptor.forClass(String.class);
        final ArgumentCaptor<String> payloadCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockedKarkaTemplate, times(2)).send(topicCaptor.capture(), payloadCaptor.capture());

        topicCaptor.getAllValues().forEach(v -> assertEquals(TEST_TOPIC, v));

        final String rawCloudEvent1 = payloadCaptor.getAllValues().get(0);
        final String rawCloudEvent2 = payloadCaptor.getAllValues().get(1);
        final CloudEvent cloudEvent1 = OBJECT_MAPPER.readValue(rawCloudEvent1, CloudEvent.class);
        final CloudEvent cloudEvent2 = OBJECT_MAPPER.readValue(rawCloudEvent2, CloudEvent.class);

        assertEquals("id", cloudEvent1.getId());
        assertEquals("id", cloudEvent2.getId());
    }

    private DecisionModelResource makeModel() {
        final DecisionModelResource model = mock(DecisionModelResource.class);
        when(model.getGav()).thenReturn(new KogitoGAV("groupId", "artifactId", "version"));
        when(model.getModelName()).thenReturn("name");
        when(model.getNamespace()).thenReturn("namespace");
        when(model.getModelMetadata()).thenReturn(
                new DecisionModelMetadata("http://www.omg.org/spec/DMN/20151101/dmn.xsd"));
        when(model.get()).thenReturn("model");
        return model;
    }
}
