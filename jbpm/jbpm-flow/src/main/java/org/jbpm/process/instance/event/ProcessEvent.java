package org.jbpm.process.instance.event;

import java.util.Date;
import java.util.EventObject;

import org.kie.api.runtime.KieRuntime;
import org.kie.api.runtime.process.ProcessInstance;

public class ProcessEvent extends EventObject {

    private static final long serialVersionUID = 510l;

    private final KieRuntime kruntime;
    private final Date eventDate;
    private final String eventIdentity;

    public ProcessEvent(final ProcessInstance instance, final KieRuntime kruntime) {
        this(instance, kruntime, null);
    }

    public ProcessEvent(final ProcessInstance instance, final KieRuntime kruntime, final String identity) {
        super(instance);
        this.kruntime = kruntime;
        this.eventDate = new Date();
        this.eventIdentity = identity;
    }

    public ProcessInstance getProcessInstance() {
        return (ProcessInstance) getSource();
    }

    public KieRuntime getKieRuntime() {
        return kruntime;
    }

    public Date getEventDate() {
        return this.eventDate;
    }

    public String getEventIdentity() {
        return eventIdentity;
    }
}
