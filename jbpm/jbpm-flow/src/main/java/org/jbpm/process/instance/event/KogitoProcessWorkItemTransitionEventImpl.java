package org.jbpm.process.instance.event;

import org.kie.api.runtime.KieRuntime;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.kogito.internal.process.event.ProcessWorkItemTransitionEvent;
import org.kie.kogito.internal.process.runtime.KogitoWorkItem;
import org.kie.kogito.process.workitem.Transition;

public class KogitoProcessWorkItemTransitionEventImpl extends ProcessEvent
        implements ProcessWorkItemTransitionEvent {
    private static final long serialVersionUID = 510l;

    private KogitoWorkItem workItem;
    private Transition<?> transition;
    private boolean transitioned;

    public KogitoProcessWorkItemTransitionEventImpl(ProcessInstance instance, KogitoWorkItem workItem, Transition<?> transition, KieRuntime kruntime, boolean transitioned, String identity) {
        super(instance, kruntime, identity);
        this.workItem = workItem;
        this.transition = transition;
        this.transitioned = transitioned;
    }

    @Override
    public KogitoWorkItem getWorkItem() {
        return workItem;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("==>[WorkItemTransition(id=" + getWorkItem().getStringId());
        if (transition != null) {
            sb.append(" phase=" + transition.phase() + ")]");
        }
        return sb.toString();
    }

    @Override
    public Transition<?> getTransition() {
        return transition;
    }

    @Override
    public boolean isTransitioned() {
        return transitioned;
    }

}
