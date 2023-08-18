package org.kie.kogito.serverless.workflow.operationid;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WorkflowOperationIdFactoryProviderTest {

    @Test
    void testFactories() {
        assertThat(WorkflowOperationIdFactoryProvider.getFactory(Optional.of("full_uri"))).isInstanceOf(URIWorkflowOperationIdFactory.class);
        assertThat(WorkflowOperationIdFactoryProvider.getFactory(Optional.of("function_name"))).isInstanceOf(FunctionWorkflowOperationIdFactory.class);
        assertThat(WorkflowOperationIdFactoryProvider.getFactory(Optional.of("file_name"))).isInstanceOf(FileNameWorkflowOperationIdFactory.class);
        assertThat(WorkflowOperationIdFactoryProvider.getFactory(Optional.empty())).isInstanceOf(FileNameWorkflowOperationIdFactory.class);
        assertThat(WorkflowOperationIdFactoryProvider.getFactory(Optional.of("random"))).isInstanceOf(FileNameWorkflowOperationIdFactory.class);
    }
}
