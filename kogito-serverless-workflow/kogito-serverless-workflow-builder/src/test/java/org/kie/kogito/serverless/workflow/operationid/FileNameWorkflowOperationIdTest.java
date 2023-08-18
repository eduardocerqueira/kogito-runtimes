package org.kie.kogito.serverless.workflow.operationid;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.serverlessworkflow.api.Workflow;
import io.serverlessworkflow.api.functions.FunctionDefinition;
import io.serverlessworkflow.api.functions.FunctionDefinition.Type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

class FileNameWorkflowOperationIdTest {

    private Workflow workflow;
    private WorkflowOperationIdFactory factory;
    private FunctionDefinition definition;

    @BeforeEach
    void setup() {
        workflow = mock(Workflow.class);
        definition = new FunctionDefinition("pepe");
        factory = WorkflowOperationIdFactoryProvider.getFactory(Optional.of(FileNameWorkflowOperationIdFactory.FILE_PROP_VALUE));
    }

    @Test
    void testOperationId() {
        definition.setType(Type.REST);
        definition.setOperation("http://myserver.com/spec/PePE1.yaml#doSomething");
        WorkflowOperationId id = factory.from(workflow, definition, Optional.empty());
        assertThat(id.getOperation()).isEqualTo("doSomething");
        assertThat(id.getFileName()).isEqualTo("PePE1.yaml");
        assertThat(id.getPackageName()).isEqualTo("pepe");
        assertThat(id.getUri()).hasToString("http://myserver.com/spec/PePE1.yaml");
        assertThat(id.getService()).isNull();
    }

    @Test
    void testOperationIdWithService() {
        definition.setType(Type.RPC);
        definition.setOperation("http://myserver.com/spec/PePE1.yaml#service#doSomething");
        WorkflowOperationId id = factory.from(workflow, definition, Optional.empty());
        assertThat(id.getOperation()).isEqualTo("doSomething");
        assertThat(id.getFileName()).isEqualTo("PePE1.yaml");
        assertThat(id.getPackageName()).isEqualTo("pepe");
        assertThat(id.getUri()).hasToString("http://myserver.com/spec/PePE1.yaml");
        assertThat(id.getService()).isEqualTo("service");
    }

    @Test
    void testEmptyFileName() {
        definition.setType(Type.REST);
        definition.setOperation("file://wrongUri.yaml#doSomething");
        assertThatThrownBy(() -> factory.from(workflow, definition, Optional.empty())).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Empty file name");
    }
}
