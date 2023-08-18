package org.kie.kogito.task.management.service;

import org.kie.kogito.process.workitem.Policy;

public interface TaskManagementOperations {

    TaskInfo updateTask(String processId,
            String processInstanceId,
            String taskId,
            TaskInfo taskInfo,
            boolean replace,
            Policy<?>... policies);

    TaskInfo getTask(String processId, String processInstanceId, String taskId, Policy<?>... policies);
}
