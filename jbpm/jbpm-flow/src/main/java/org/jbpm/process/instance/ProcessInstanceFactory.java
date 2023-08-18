package org.jbpm.process.instance;

import java.util.Map;

import org.drools.core.common.InternalKnowledgeRuntime;
import org.kie.api.definition.process.Process;
import org.kie.internal.process.CorrelationKey;

/**
 * 
 */
public interface ProcessInstanceFactory {

    ProcessInstance createProcessInstance(Process process, CorrelationKey correlationKey,
            InternalKnowledgeRuntime kruntime, Map<String, Object> parameters);

}
