package org.kie.kogito.internal.process.event;

import org.kie.api.event.process.ProcessEvent;
import org.kie.kogito.internal.process.runtime.KogitoWorkItem;
import org.kie.kogito.process.workitem.Transition;

/**
 * An event when a work item has transition between life cycle phases
 */
public interface ProcessWorkItemTransitionEvent
        extends
        ProcessEvent {

    /**
     * Returns work item being transitioned
     * 
     * @return work item
     */
    KogitoWorkItem getWorkItem();

    /**
     * Returns transition that is applied to the work item
     * 
     * @return transition
     */
    Transition<?> getTransition();

    /**
     * Indicated is the transition has already been done.
     * 
     * @return true if transition has already been done, otherwise false
     */
    boolean isTransitioned();
}