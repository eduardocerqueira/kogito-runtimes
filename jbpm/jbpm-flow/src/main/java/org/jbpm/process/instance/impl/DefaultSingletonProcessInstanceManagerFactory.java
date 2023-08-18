package org.jbpm.process.instance.impl;

import org.drools.core.common.InternalKnowledgeRuntime;
import org.jbpm.process.instance.ProcessInstanceManager;
import org.jbpm.process.instance.ProcessInstanceManagerFactory;

public class DefaultSingletonProcessInstanceManagerFactory implements ProcessInstanceManagerFactory {

    private static ProcessInstanceManager instance = new DefaultProcessInstanceManager();

    public ProcessInstanceManager createProcessInstanceManager(InternalKnowledgeRuntime kruntime) {
        return instance;
    }

}
