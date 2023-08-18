package org.jbpm.process.instance.event;

import org.kie.api.event.process.ProcessCompletedEvent;
import org.kie.api.runtime.KieRuntime;
import org.kie.api.runtime.process.ProcessInstance;

public class ProcessCompletedEventImpl extends ProcessEvent implements ProcessCompletedEvent {

    private static final long serialVersionUID = 510l;

    public ProcessCompletedEventImpl(ProcessInstance instance, KieRuntime kruntime, String identity) {
        super(instance, kruntime, identity);
    }

    @Override
    public String toString() {
        return "==>[ProcessCompleted(name=" + getProcessInstance().getProcessName() + "; id=" + getProcessInstance().getProcessId() + ")]";
    }
}
