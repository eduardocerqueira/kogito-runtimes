package org.kie.kogito.dmn;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import org.junit.jupiter.api.Test;
import org.kie.kogito.KogitoGAV;
import org.kie.kogito.ModelDomain;
import org.kie.kogito.decision.DecisionModelMetadata;
import org.kie.kogito.decision.DecisionModelResource;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultDecisionModelResourceTest {

    private static final KogitoGAV GAV = new KogitoGAV("groupID", "artifactId", "version");

    private static final String CONTENT = "content";

    @Test
    public void testGetters() {
        final DecisionModelResource resource = new DefaultDecisionModelResource(GAV,
                "namespace",
                "name",
                new DecisionModelMetadata("http://www.omg.org/spec/DMN/20151101/dmn.xsd"),
                new InputStreamReader(new ByteArrayInputStream(CONTENT.getBytes())));
        assertThat(resource.getGav()).isEqualTo(GAV);
        assertThat(resource.getModelName()).isEqualTo("name");
        assertThat(resource.getNamespace()).isEqualTo("namespace");
        assertThat(resource.getModelMetadata().getModelDomain()).isEqualTo(ModelDomain.DECISION);
        assertThat(resource.getModelMetadata().getSpecVersion()).isEqualTo("http://www.omg.org/spec/DMN/20151101/dmn.xsd");
    }

    @Test
    public void testLoad() {
        final DecisionModelResource resource = new DefaultDecisionModelResource(GAV,
                "namespace",
                "name",
                new DecisionModelMetadata("http://www.omg.org/spec/DMN/20151101/dmn.xsd"),
                new InputStreamReader(new ByteArrayInputStream(CONTENT.getBytes())));
        assertThat(resource.get().trim()).isEqualTo(CONTENT);
    }
}
