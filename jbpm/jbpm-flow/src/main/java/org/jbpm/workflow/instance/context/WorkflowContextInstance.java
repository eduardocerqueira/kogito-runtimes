package org.jbpm.workflow.instance.context;

import org.jbpm.process.instance.ContextInstance;
import org.jbpm.workflow.instance.NodeInstanceContainer;

public interface WorkflowContextInstance extends ContextInstance {

    NodeInstanceContainer getNodeInstanceContainer();

    void setNodeInstanceContainer(NodeInstanceContainer nodeInstanceContainer);

}
