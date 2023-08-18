package org.kie.kogito.internal.process.event;

import java.util.List;
import java.util.Map;

import org.kie.api.runtime.KieRuntime;
import org.kie.kogito.internal.process.runtime.KogitoNodeInstance;
import org.kie.kogito.internal.process.runtime.KogitoProcessInstance;
import org.kie.kogito.internal.process.runtime.KogitoWorkItem;
import org.kie.kogito.process.workitem.HumanTaskWorkItem;
import org.kie.kogito.process.workitem.Transition;

public interface KogitoProcessEventSupport {

    void fireBeforeProcessStarted(KogitoProcessInstance instance, KieRuntime kruntime);

    void fireAfterProcessStarted(KogitoProcessInstance instance, KieRuntime kruntime);

    void fireBeforeProcessCompleted(KogitoProcessInstance instance, KieRuntime kruntime);

    void fireAfterProcessCompleted(KogitoProcessInstance instance, KieRuntime kruntime);

    void fireBeforeNodeTriggered(KogitoNodeInstance nodeInstance, KieRuntime kruntime);

    void fireAfterNodeTriggered(KogitoNodeInstance nodeInstance, KieRuntime kruntime);

    void fireBeforeNodeLeft(KogitoNodeInstance nodeInstance, KieRuntime kruntime);

    void fireAfterNodeLeft(KogitoNodeInstance nodeInstance, KieRuntime kruntime);

    void fireBeforeVariableChanged(String id, String instanceId, Object oldValue, Object newValue, List<String> tags,
            KogitoProcessInstance processInstance, KogitoNodeInstance nodeInstance, KieRuntime kruntime);

    void fireAfterVariableChanged(String name, String id, Object oldValue, Object newValue, List<String> tags,
            KogitoProcessInstance processInstance, KogitoNodeInstance nodeInstance, KieRuntime kruntime);

    void fireBeforeSLAViolated(KogitoProcessInstance instance, KieRuntime kruntime);

    void fireAfterSLAViolated(KogitoProcessInstance instance, KieRuntime kruntime);

    void fireBeforeSLAViolated(KogitoProcessInstance instance, KogitoNodeInstance nodeInstance, KieRuntime kruntime);

    void fireAfterSLAViolated(KogitoProcessInstance instance, KogitoNodeInstance nodeInstance, KieRuntime kruntime);

    void fireBeforeWorkItemTransition(KogitoProcessInstance instance, KogitoWorkItem workitem, Transition<?> transition, KieRuntime kruntime);

    void fireAfterWorkItemTransition(KogitoProcessInstance instance, KogitoWorkItem workitem, Transition<?> transition, KieRuntime kruntime);

    void fireOnSignal(KogitoProcessInstance instance, KogitoNodeInstance nodeInstance, KieRuntime kruntime, String signalName, Object signalObject);

    void fireOnMessage(KogitoProcessInstance instance, KogitoNodeInstance nodeInstance, KieRuntime kruntime, String messageName, Object messageObject);

    void fireOnTaskNotStartedDeadline(KogitoProcessInstance instance,
            HumanTaskWorkItem workItem,
            Map<String, Object> notification,
            KieRuntime kruntime);

    void fireOnTaskNotCompletedDeadline(KogitoProcessInstance instance,
            HumanTaskWorkItem workItem,
            Map<String, Object> notification,
            KieRuntime kruntime);

    void reset();

    void addEventListener(KogitoProcessEventListener listener);

    void removeEventListener(KogitoProcessEventListener listener);
}