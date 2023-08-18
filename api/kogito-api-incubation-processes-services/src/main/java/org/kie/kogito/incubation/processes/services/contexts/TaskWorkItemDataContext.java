package org.kie.kogito.incubation.processes.services.contexts;

import java.util.List;
import java.util.stream.Collectors;

import org.kie.kogito.incubation.common.DefaultCastable;
import org.kie.kogito.incubation.common.MetaDataContext;
import org.kie.kogito.incubation.processes.ProcessIdParser;
import org.kie.kogito.incubation.processes.TaskInstanceId;

public class TaskWorkItemDataContext implements MetaDataContext, DefaultCastable {
    private List<String> tasks;

    public List<TaskInstanceId> tasks() {
        return tasks.stream().map(t -> ProcessIdParser.parse(t, TaskInstanceId.class)).collect(Collectors.toList());
    }

    void setTasks(List<String> tasks) {
        this.tasks = tasks;
    }
}
