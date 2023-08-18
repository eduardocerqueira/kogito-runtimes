package org.jbpm.process.instance.event;

import java.util.Collections;
import java.util.List;

import org.kie.api.runtime.KieRuntime;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.kogito.internal.process.event.KogitoProcessVariableChangedEvent;
import org.kie.kogito.internal.process.runtime.KogitoNodeInstance;

public class KogitoProcessVariableChangedEventImpl extends ProcessEvent implements KogitoProcessVariableChangedEvent {

    private String id;
    private String instanceId;
    private Object oldValue;
    private Object newValue;
    private List<String> tags;
    private KogitoNodeInstance nodeInstance;

    public KogitoProcessVariableChangedEventImpl(final String id, final String instanceId,
            final Object oldValue, final Object newValue, List<String> tags,
            final ProcessInstance processInstance, KogitoNodeInstance nodeInstance, KieRuntime kruntime, String identity) {
        super(processInstance, kruntime, identity);
        this.id = id;
        this.instanceId = instanceId;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.tags = tags == null ? Collections.emptyList() : tags;
        this.nodeInstance = nodeInstance;
    }

    public String getVariableInstanceId() {
        return instanceId;
    }

    public String getVariableId() {
        return id;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public boolean hasTag(String tag) {
        return tags.contains(tag);
    }

    public List<String> getTags() {
        return tags;
    }

    @Override
    public KogitoNodeInstance getNodeInstance() {
        return this.nodeInstance;
    }

    @Override
    public String toString() {
        return "==>[ProcessVariableChanged(id=" + getVariableId() + "; instanceId=" + getVariableInstanceId() + "; oldValue=" + getOldValue() + "; newValue=" + getNewValue()
                + "; processName=" + getProcessInstance().getProcessName() + "; processId=" + getProcessInstance().getProcessId() + ")]";
    }
}