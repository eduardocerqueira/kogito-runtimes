package org.jbpm.workflow.instance;

import java.util.List;
import java.util.Map;

import org.jbpm.process.instance.ProcessInstance;
import org.kie.kogito.internal.process.event.KogitoEventListener;
import org.kie.kogito.internal.process.runtime.KogitoWorkflowProcessInstance;

public interface WorkflowProcessInstance extends ProcessInstance,
        KogitoWorkflowProcessInstance,
        NodeInstanceContainer {

    void addEventListener(String type, KogitoEventListener eventListener, boolean external);

    void removeEventListener(String type, KogitoEventListener eventListener, boolean external);

    void setHeaders(Map<String, List<String>> headers);
}
