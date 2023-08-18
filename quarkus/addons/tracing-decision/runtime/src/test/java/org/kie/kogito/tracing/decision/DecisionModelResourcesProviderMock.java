package org.kie.kogito.tracing.decision;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

import org.kie.kogito.KogitoGAV;
import org.kie.kogito.decision.DecisionModelMetadata;
import org.kie.kogito.decision.DecisionModelResource;
import org.kie.kogito.decision.DecisionModelResourcesProvider;
import org.kie.kogito.dmn.DefaultDecisionModelResource;

import io.quarkus.test.Mock;

import static org.kie.kogito.tracing.decision.QuarkusDecisionTracingTest.TEST_MODEL_NAME;
import static org.kie.kogito.tracing.decision.QuarkusDecisionTracingTest.TEST_MODEL_NAMESPACE;

@Mock
public class DecisionModelResourcesProviderMock implements DecisionModelResourcesProvider {

    private static final String CONTENT = "content";

    @Override
    public List<DecisionModelResource> get() {
        DecisionModelResource resource = new DefaultDecisionModelResource(
                new KogitoGAV("test", "test", "test"),
                TEST_MODEL_NAMESPACE,
                TEST_MODEL_NAME,
                new DecisionModelMetadata(
                        "http://www.omg.org/spec/DMN/20151101/dmn.xsd"),
                new InputStreamReader(new ByteArrayInputStream(CONTENT.getBytes())));

        return Collections.singletonList(resource);
    }
}
