package org.kie.kogito.internal.process.runtime;

import org.kie.kogito.process.workitem.Transition;

public interface KogitoWorkItemHandler {

    /**
     * The given work item should be executed.
     *
     * @param workItem the work item that should be executed
     * @param manager the manager that requested the work item to be executed
     */
    void executeWorkItem(KogitoWorkItem workItem, KogitoWorkItemManager manager);

    /**
     * The given work item should be aborted.
     *
     * @param workItem the work item that should be aborted
     * @param manager the manager that requested the work item to be aborted
     */
    void abortWorkItem(KogitoWorkItem workItem, KogitoWorkItemManager manager);

    /**
     * Returns name that it should be registered with, by default simple class name;
     *
     * @return name that should this handler be registered with
     */
    default String getName() {
        return getClass().getSimpleName();
    }

    default void transitionToPhase(KogitoWorkItem workItem, KogitoWorkItemManager manager, Transition<?> transition) {
        throw new UnsupportedOperationException();
    }
}
