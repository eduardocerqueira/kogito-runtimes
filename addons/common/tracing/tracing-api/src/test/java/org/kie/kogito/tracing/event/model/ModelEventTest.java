package org.kie.kogito.tracing.event.model;

import org.junit.jupiter.api.Test;
import org.kie.kogito.KogitoGAV;
import org.kie.kogito.ModelDomain;
import org.kie.kogito.event.ModelMetadata;

import static org.assertj.core.api.Assertions.assertThat;

public class ModelEventTest {

    @Test
    public void testGetters() {
        final KogitoGAV gav = new KogitoGAV("groupID", "artifactId", "version");
        final ModelEvent e = new ModelEvent(gav,
                "name", new ModelMetadata(ModelDomain.DECISION) {
                }, ModelDomain.DECISION) {

        };

        assertThat(e.getGav().getGroupId()).isEqualTo(gav.getGroupId());
        assertThat(e.getGav().getArtifactId()).isEqualTo(gav.getArtifactId());
        assertThat(e.getGav().getVersion()).isEqualTo(gav.getVersion());
        assertThat(e.getName()).isEqualTo("name");
    }
}
