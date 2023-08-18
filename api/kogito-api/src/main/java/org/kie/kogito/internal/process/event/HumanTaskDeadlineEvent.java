package org.kie.kogito.internal.process.event;

import java.util.Map;

import org.kie.api.event.process.ProcessEvent;
import org.kie.kogito.process.workitem.HumanTaskWorkItem;

/**
 * An event when a dealine for task has expired
 */
public interface HumanTaskDeadlineEvent
        extends
        ProcessEvent {

    enum DeadlineType {
        Started,
        Completed
    }

    /**
     * Returns work item which timeout expires
     * 
     * @return work item
     */
    HumanTaskWorkItem getWorkItem();

    /**
     * Returns notification data
     * 
     * @return key-value pair list
     */
    Map<String, Object> getNotification();

    /**
     * Returns dealine type
     * 
     * @return not started or not completed
     */
    DeadlineType getType();
}