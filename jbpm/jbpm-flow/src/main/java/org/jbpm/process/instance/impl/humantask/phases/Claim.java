package org.jbpm.process.instance.impl.humantask.phases;

import java.util.Arrays;
import java.util.List;

import org.jbpm.process.instance.impl.humantask.HumanTaskWorkItemImpl;
import org.jbpm.process.instance.impl.workitem.Active;
import org.kie.kogito.auth.SecurityPolicy;
import org.kie.kogito.internal.process.runtime.KogitoWorkItem;
import org.kie.kogito.process.workitem.HumanTaskWorkItem;
import org.kie.kogito.process.workitem.LifeCyclePhase;
import org.kie.kogito.process.workitem.Policy;
import org.kie.kogito.process.workitem.Transition;

/**
 * Claim life cycle phase that applies to human tasks.
 * It will set the status to "Reserved" and assign actual owner if there is security
 * context available.
 *
 * It can transition from
 * <ul>
 * <li>Active</li>
 * </ul>
 */
public class Claim implements LifeCyclePhase {

    public static final String ID = "claim";
    public static final String STATUS = "Reserved";

    private List<String> allowedTransitions = Arrays.asList(Active.ID, Release.ID);

    @Override
    public String id() {
        return ID;
    }

    @Override
    public String status() {
        return STATUS;
    }

    @Override
    public boolean isTerminating() {
        return false;
    }

    @Override
    public boolean canTransition(LifeCyclePhase phase) {
        return allowedTransitions.contains(phase.id());
    }

    @Override
    public void apply(KogitoWorkItem workitem, Transition<?> transition) {
        if (transition.policies() != null) {
            for (Policy<?> policy : transition.policies()) {
                if (policy instanceof SecurityPolicy) {
                    ((HumanTaskWorkItemImpl) workitem).setActualOwner(((SecurityPolicy) policy).value().getName());
                    break;
                }
            }
        }
        workitem.getResults().put("ActorId", ((HumanTaskWorkItem) workitem).getActualOwner());
    }

}
