package org.kie.kogito.serverless.workflow.operationid;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.serverlessworkflow.api.Workflow;
import io.serverlessworkflow.api.functions.FunctionDefinition;
import io.serverlessworkflow.api.functions.FunctionDefinition.Type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class FullURIWorkflowOperationIdTest {

    private Workflow workflow;
    private FunctionDefinition definition;
    private WorkflowOperationIdFactory factory;

    @BeforeEach
    void setup() {
        workflow = mock(Workflow.class);
        definition = new FunctionDefinition("pepe");
        factory = WorkflowOperationIdFactoryProvider.getFactory(Optional.of(URIWorkflowOperationIdFactory.URI_PROP_VALUE));
    }

    @Test
    void testOperationId() {
        definition.setType(Type.REST);
        definition.setOperation("http://myserver.com/spec/PePE1.yaml#doSomething");
        WorkflowOperationId id = factory.from(workflow, definition, Optional.empty());
        assertThat(id.getOperation()).isEqualTo("doSomething");
        assertThat(id.getFileName()).isEqualTo("/spec/PePE1.yaml");
        assertThat(id.getPackageName()).isEqualTo("specpepe");
        assertThat(id.getUri()).hasToString("http://myserver.com/spec/PePE1.yaml");
        assertThat(id.getService()).isNull();
    }

}
