package org.kie.kogito.process.workitem;

/**
 * Process view of a work item.<br>
 * It includes only parameters and results as defined in process plus getters to get the active phase.<br>
 * 
 * @param <P> Input generated class
 * @param <R> Output generated class
 */
public interface TaskModel<P, R> {

    String getId();

    String getName();

    int getState();

    String getPhase();

    String getPhaseStatus();

    P getParameters();

    R getResults();
}
