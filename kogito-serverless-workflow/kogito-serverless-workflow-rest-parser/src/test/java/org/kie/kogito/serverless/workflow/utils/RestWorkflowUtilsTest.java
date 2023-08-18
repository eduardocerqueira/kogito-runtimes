package org.kie.kogito.serverless.workflow.utils;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.codegen.api.context.impl.JavaKogitoBuildContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.kie.kogito.serverless.workflow.utils.RestWorkflowUtils.URL;
import static org.kie.kogito.serverless.workflow.utils.RestWorkflowUtils.getOpenApiProperty;

public class RestWorkflowUtilsTest {

    private static final String TEST_RESOURCES = "src/test/resources";
    KogitoBuildContext context;

    @BeforeEach
    protected void setup() {
        context = JavaKogitoBuildContext.builder()
                .withApplicationProperties(new File(TEST_RESOURCES))
                .withPackageName(this.getClass().getPackage().getName())
                .build();
    }

    @Test
    public void testResolveOpenAPIMetadata() {
        assertThat(getOpenApiProperty("testfunction", URL, context, String.class, "http://localhost:8080")).isEqualTo("http://localhost:8282");
        assertThat(getOpenApiProperty("testfunction1", "base_path2", context, Integer.class, 0)).isZero();
    }
}
