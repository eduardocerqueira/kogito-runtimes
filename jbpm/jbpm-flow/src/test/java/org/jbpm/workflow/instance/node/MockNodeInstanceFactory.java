/**
 * 
 */
package org.jbpm.workflow.instance.node;

import org.jbpm.workflow.instance.WorkflowProcessInstance;
import org.jbpm.workflow.instance.impl.NodeInstanceFactory;
import org.kie.api.definition.process.Node;
import org.kie.api.runtime.process.NodeInstance;
import org.kie.api.runtime.process.NodeInstanceContainer;
import org.kie.kogito.internal.process.runtime.KogitoNodeInstanceContainer;

public class MockNodeInstanceFactory implements NodeInstanceFactory {

    private MockNodeInstance instance;

    public MockNodeInstanceFactory(MockNodeInstance instance) {
        this.instance = instance;
    }

    public MockNodeInstance getMockNodeInstance() {
        return this.instance;
    }

    public NodeInstance getNodeInstance(Node node, WorkflowProcessInstance processInstance, NodeInstanceContainer nodeInstanceContainer) {
        instance.setProcessInstance(processInstance);
        instance.setNodeInstanceContainer((KogitoNodeInstanceContainer) nodeInstanceContainer);
        return instance;
    }

}
