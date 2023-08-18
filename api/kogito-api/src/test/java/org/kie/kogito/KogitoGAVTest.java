package org.kie.kogito;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

class KogitoGAVTest {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    void serializationTest() throws JsonProcessingException {
        KogitoGAV originalGav = new KogitoGAV("group", "artifact", "version");

        KogitoGAV processedGav = MAPPER.readValue(MAPPER.writeValueAsString(originalGav), KogitoGAV.class);

        assertThat(processedGav).isNotNull();
        assertThat(processedGav.getGroupId()).isEqualTo(originalGav.getGroupId());
        assertThat(processedGav.getArtifactId()).isEqualTo(originalGav.getArtifactId());
        assertThat(processedGav.getVersion()).isEqualTo(originalGav.getVersion());
        assertThat(processedGav).isEqualTo(originalGav);
    }
}
