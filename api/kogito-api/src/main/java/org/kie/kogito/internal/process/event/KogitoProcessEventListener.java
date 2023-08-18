package org.kie.kogito.internal.process.event;

import org.kie.api.event.process.ProcessEventListener;

public interface KogitoProcessEventListener extends ProcessEventListener {

    /**
     * This listener method is invoked right before a work item transition.
     * 
     * @param event
     */
    default void beforeWorkItemTransition(ProcessWorkItemTransitionEvent event) {
    };

    /**
     * This listener method is invoked right after a work item transition.
     * 
     * @param event
     */
    default void afterWorkItemTransition(ProcessWorkItemTransitionEvent event) {
    }

    default void onHumanTaskDeadline(HumanTaskDeadlineEvent event) {
    }
}
