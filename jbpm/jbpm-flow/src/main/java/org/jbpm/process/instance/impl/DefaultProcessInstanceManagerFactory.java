package org.jbpm.process.instance.impl;

import org.drools.core.common.InternalKnowledgeRuntime;
import org.jbpm.process.instance.ProcessInstanceManager;
import org.jbpm.process.instance.ProcessInstanceManagerFactory;

public class DefaultProcessInstanceManagerFactory implements ProcessInstanceManagerFactory {

    public ProcessInstanceManager createProcessInstanceManager(InternalKnowledgeRuntime kruntime) {
        return new DefaultProcessInstanceManager();
    }

}
