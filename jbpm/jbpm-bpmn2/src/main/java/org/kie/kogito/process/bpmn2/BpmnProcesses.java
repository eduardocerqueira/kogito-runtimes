package org.kie.kogito.process.bpmn2;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.kie.kogito.Model;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.Processes;

public class BpmnProcesses implements Processes {

    private Map<String, Process<? extends Model>> mappedProcesses = new HashMap<>();

    public BpmnProcesses addProcess(Process<? extends Model> process) {
        mappedProcesses.put(process.id(), process);
        return this;
    }

    @Override
    public Process<? extends Model> processById(String processId) {
        return mappedProcesses.get(processId);
    }

    @Override
    public Collection<String> processIds() {
        return mappedProcesses.keySet();
    }
}
