package org.jbpm.process.instance.event;

import org.kie.api.event.process.ProcessStartedEvent;
import org.kie.api.runtime.KieRuntime;
import org.kie.api.runtime.process.ProcessInstance;

public class ProcessStartedEventImpl extends ProcessEvent implements ProcessStartedEvent {

    private static final long serialVersionUID = 510l;

    public ProcessStartedEventImpl(ProcessInstance instance, KieRuntime kruntime, String identity) {
        super(instance, kruntime, identity);
    }

    @Override
    public String toString() {
        return "==>[ProcessStarted(name=" + getProcessInstance().getProcessName() + "; id=" + getProcessInstance().getProcessId() + ")]";
    }

}