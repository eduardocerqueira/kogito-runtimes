package org.kie.kogito.process.workitems;

import java.util.Date;

import org.kie.kogito.internal.process.runtime.KogitoNodeInstance;
import org.kie.kogito.internal.process.runtime.KogitoProcessInstance;

public interface InternalKogitoWorkItem extends org.drools.core.process.WorkItem, org.kie.kogito.internal.process.runtime.KogitoWorkItem {

    void setProcessInstanceId(String processInstanceId);

    void setNodeInstanceId(String deploymentId);

    String getNodeInstanceStringId();

    void setPhaseId(String phaseId);

    void setPhaseStatus(String phaseStatus);

    void setStartDate(Date date);

    void setCompleteDate(Date date);

    void setNodeInstance(KogitoNodeInstance nodeInstance);

    void setProcessInstance(KogitoProcessInstance processInstance);
}
