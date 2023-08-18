package org.jbpm.process.instance;

import org.drools.core.common.InternalKnowledgeRuntime;

public interface ProcessInstanceManagerFactory {

    ProcessInstanceManager createProcessInstanceManager(InternalKnowledgeRuntime kruntime);

}
