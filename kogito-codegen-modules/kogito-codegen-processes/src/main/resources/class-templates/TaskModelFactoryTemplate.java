package org.jbpm.process.codegen;

import org.kie.kogito.process.workitem.TaskModel;

public class $TaskModelFactory$ {

    public static TaskModel from(org.kie.kogito.process.WorkItem workItem) {
        switch (workItem.getNodeId()) {
            default:
                throw new IllegalArgumentException("Invalid task name for work item "+workItem);
        }
    }
}