package org.jbpm.workflow.instance.impl;

import org.jbpm.workflow.instance.WorkflowProcessInstance;
import org.kie.api.definition.process.Node;
import org.kie.api.runtime.process.NodeInstance;
import org.kie.api.runtime.process.NodeInstanceContainer;

public interface NodeInstanceFactory {

    NodeInstance getNodeInstance(Node node, WorkflowProcessInstance processInstance, NodeInstanceContainer nodeInstanceContainer);

}
