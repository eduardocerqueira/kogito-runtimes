package org.jbpm.process.instance.impl.humantask.phases;

import java.util.Arrays;
import java.util.List;

import org.jbpm.process.instance.impl.humantask.HumanTaskWorkItemImpl;
import org.kie.kogito.internal.process.runtime.KogitoWorkItem;
import org.kie.kogito.process.workitem.LifeCyclePhase;
import org.kie.kogito.process.workitem.Transition;

/**
 * Release life cycle phase that applies to human tasks.
 * It will set the status to "Ready" and resets actual owner
 *
 * It can transition from
 * <ul>
 * <li>Claim</li>
 * </ul>
 */
public class Release implements LifeCyclePhase {

    public static final String ID = "release";
    public static final String STATUS = "Ready";

    private List<String> allowedTransitions = Arrays.asList(Claim.ID);

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

        ((HumanTaskWorkItemImpl) workitem).setActualOwner(null);
    }
}
