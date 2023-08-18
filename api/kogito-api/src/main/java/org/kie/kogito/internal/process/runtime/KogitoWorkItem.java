package org.kie.kogito.internal.process.runtime;

import java.util.Date;

import org.kie.api.runtime.process.WorkItem;
import org.kie.kogito.process.workitem.Policy;

public interface KogitoWorkItem extends WorkItem {

    @Override
    @Deprecated
    long getId();

    String getStringId();

    /**
     * The id of the process instance that requested the execution of this
     * work item
     *
     * @return the id of the related process instance
     */
    String getProcessInstanceStringId();

    /**
     * Returns optional life cycle phase id associated with this work item
     * 
     * @return optional life cycle phase id
     */
    String getPhaseId();

    /**
     * Returns optional life cycle phase status associated with this work item
     * 
     * @return optional life cycle phase status
     */
    String getPhaseStatus();

    /**
     * Returns timestamp indicating the start date of this work item
     * 
     * @return start date
     */
    Date getStartDate();

    /**
     * Returns timestamp indicating the completion date of this work item
     * 
     * @return completion date
     */
    Date getCompleteDate();

    /**
     * The node instance that is associated with this
     * work item
     *
     * @return the related node instance
     */
    KogitoNodeInstance getNodeInstance();

    /**
     * The process instance that requested the execution of this
     * work item
     *
     * @return the related process instance
     */
    KogitoProcessInstance getProcessInstance();

    /**
     * Enforces given policies on this work item. It must false in case of any policy
     * violations.
     * 
     * @param policies optional policies to be enforced
     * @return return true if this work item can enforce all policies otherwise false
     */
    default boolean enforce(Policy<?>... policies) {
        return true;
    }
}