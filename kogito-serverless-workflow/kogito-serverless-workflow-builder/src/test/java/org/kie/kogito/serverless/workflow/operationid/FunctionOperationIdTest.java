package org.kie.kogito.serverless.workflow.operationid;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.serverlessworkflow.api.Workflow;
import io.serverlessworkflow.api.functions.FunctionDefinition;
import io.serverlessworkflow.api.functions.FunctionDefinition.Type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FunctionOperationIdTest {

    private Workflow workflow;
    private FunctionDefinition definition;
    private WorkflowOperationIdFactory factory;

    @BeforeEach
    void setup() {
        workflow = mock(Workflow.class);
        when(workflow.getId()).thenReturn("Test");
        definition = new FunctionDefinition("function1");
        factory = WorkflowOperationIdFactoryProvider.getFactory(Optional.of(FunctionWorkflowOperationIdFactory.FUNCTION_PROP_VALUE));
    }

    @Test
    void testOperationId() {
        definition.setType(Type.REST);
        definition.setOperation("specs/external-service.yaml#sendRequest");
        WorkflowOperationId id = factory.from(workflow, definition, Optional.empty());
        assertThat(id.getOperation()).isEqualTo("sendRequest");
        assertThat(id.getFileName()).isEqualTo("Test_function1");
        assertThat(id.getPackageName()).isEqualTo("testfunction");
        assertThat(id.getUri()).hasToString("specs/external-service.yaml");
        assertThat(id.getService()).isNull();
    }
}
