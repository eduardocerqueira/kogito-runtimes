package org.jbpm.process.instance.impl.actions;

import java.io.Serializable;

import org.jbpm.process.instance.impl.Action;
import org.kie.kogito.internal.process.runtime.KogitoProcessContext;

public class ProcessInstanceCompensationAction implements Action, Serializable {

    private static final long serialVersionUID = 1L;

    private final String activityRef;

    public ProcessInstanceCompensationAction(String activityRef) {
        this.activityRef = activityRef;
    }

    @Override
    public void execute(KogitoProcessContext context) throws Exception {
        context.getProcessInstance().signalEvent("Compensation", activityRef);
    }

    public String getActivityRef() {
        return activityRef;
    }
}
