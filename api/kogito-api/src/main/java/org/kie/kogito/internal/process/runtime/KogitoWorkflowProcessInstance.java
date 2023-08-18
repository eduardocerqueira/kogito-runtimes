package org.kie.kogito.internal.process.runtime;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.kie.api.runtime.process.WorkflowProcessInstance;
import org.kie.kogito.process.flexible.AdHocFragment;
import org.kie.kogito.process.flexible.Milestone;

public interface KogitoWorkflowProcessInstance extends WorkflowProcessInstance, KogitoProcessInstance, KogitoNodeInstanceContainer {

    /**
     * Returns start date of this process instance
     * 
     * @return actual start date
     */
    Date getStartDate();

    /**
     * Returns end date (either completed or aborted) of this process instance
     * 
     * @return actual end date
     */
    Date getEndDate();

    /**
     * Returns node definition id associated with node instance
     * that failed in case this process instance is in an error
     * 
     * @return node definition id of the failed node instance
     */
    String getNodeIdInError();

    /**
     * Returns error message associated with this process instance in case it is in an error
     * state. It will consists of
     * <ul>
     * <li>fully qualified class name of the root cause</li>
     * <li>error message of the root cause</li>
     * </ul>
     * 
     * @return error message
     */
    String getErrorMessage();

    /**
     * Returns the throwable originating the error, if available
     * 
     * @return
     */
    Optional<Throwable> getErrorCause();

    /**
     * Returns optional correlation key assigned to process instance
     * 
     * @return correlation key if present otherwise null
     */
    String getCorrelationKey();

    /**
     * Returns the list of Milestones and their status in the current process instances
     * 
     * @return Milestones defined in the process
     */
    Collection<Milestone> milestones();

    /**
     * @return AdHocFragments from the process instances
     */
    Collection<AdHocFragment> adHocFragments();

}
