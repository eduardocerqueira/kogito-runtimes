package org.kie.kogito.tracing.decision.event.model;

import org.junit.jupiter.api.Test;
import org.kie.kogito.KogitoGAV;
import org.kie.kogito.ModelDomain;
import org.kie.kogito.decision.DecisionModelMetadata;
import org.kie.kogito.tracing.event.model.models.DecisionModelEvent;

import static org.assertj.core.api.Assertions.assertThat;

public class DecisionModelEventTest {

    @Test
    public void testGetters() {
        final KogitoGAV gav = new KogitoGAV("groupID", "artifactId", "version");
        final DecisionModelEvent e = new DecisionModelEvent(
                gav,
                "name",
                "namespace",
                new DecisionModelMetadata("http://www.omg.org/spec/DMN/20151101/dmn.xsd"),
                "definition");

        assertThat(e.getGav().getGroupId()).isEqualTo(gav.getGroupId());
        assertThat(e.getGav().getArtifactId()).isEqualTo(gav.getArtifactId());
        assertThat(e.getGav().getVersion()).isEqualTo(gav.getVersion());
        assertThat(e.getName()).isEqualTo("name");
        assertThat(e.getNamespace()).isEqualTo("namespace");
        assertThat(e.getModelMetadata().getModelDomain()).isEqualTo(ModelDomain.DECISION);
        assertThat(e.getModelMetadata().getSpecVersion()).isEqualTo("http://www.omg.org/spec/DMN/20151101/dmn.xsd");
        assertThat(e.getDefinition()).isEqualTo("definition");
    }

}