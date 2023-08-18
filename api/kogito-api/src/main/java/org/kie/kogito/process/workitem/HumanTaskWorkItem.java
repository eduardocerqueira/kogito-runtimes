package org.kie.kogito.process.workitem;

import java.util.Map;
import java.util.Set;

import org.kie.kogito.internal.process.runtime.KogitoWorkItem;

/**
 * Dedicated extension to WorkItem to cover needs of human tasks
 *
 */
public interface HumanTaskWorkItem extends KogitoWorkItem {

    /**
     * Returns name of the task
     * 
     * @return task name
     */
    String getTaskName();

    /**
     * Returns optional description of the task
     * 
     * @return task description if present
     */
    String getTaskDescription();

    /**
     * Returns optional priority of the task
     * 
     * @return task priority if present
     */
    String getTaskPriority();

    /**
     * Returns reference name of the task
     * 
     * @return task reference
     */
    String getReferenceName();

    /**
     * Returns actual owner assigned to the task
     * 
     * @return task actual owner
     */
    String getActualOwner();

    /**
     * Returns potential users that can work on this task
     * 
     * @return potential users
     */
    Set<String> getPotentialUsers();

    /**
     * Returns potential groups that can work on this task
     * 
     * @return potential groups
     */
    Set<String> getPotentialGroups();

    /**
     * Returns admin users that can administer this task
     * 
     * @return admin users
     */
    Set<String> getAdminUsers();

    /**
     * Returns admin groups that can administer this task
     * 
     * @return admin groups
     */
    Set<String> getAdminGroups();

    /**
     * Returns excluded users that cannot work on this task
     * 
     * @return excluded users
     */
    Set<String> getExcludedUsers();

    /**
     * Returns task attachments
     * 
     * @return A map which key is the attachment id and value the attachment object
     */
    Map<Object, Attachment> getAttachments();

    /**
     * Returns task comments
     * 
     * @return A map which key is the comment id and value the comment object
     */
    Map<Object, Comment> getComments();

}