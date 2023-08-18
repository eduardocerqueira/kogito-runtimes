package org.kie.kogito.tracing.event.model.models;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.kie.kogito.decision.DecisionModelMetadata;
import org.kie.kogito.event.ModelMetadata;
import org.kie.kogito.tracing.event.TracingTestUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

class DecisionModelMetadataTest {

    @Test
    public void testDeserialization() throws JsonProcessingException {
        String toRead = TracingTestUtils.readResourceAsString("/decisionmodelmetadata.json");
        ModelMetadata retrieved = new ObjectMapper().readValue(toRead, ModelMetadata.class);
        assertThat(retrieved).isInstanceOf(DecisionModelMetadata.class);
    }

    @Test
    public void testSerialization() throws JsonProcessingException {
        ModelMetadata modelMetadata = getDecisionModelMetadata(new Random().nextInt(5));
        String retrieved = new ObjectMapper().writeValueAsString(modelMetadata);
        assertThat(retrieved).isNotNull();
    }

    private ModelMetadata getDecisionModelMetadata(int id) {
        return new DecisionModelMetadata("specVersion-" + id);
    }
}
