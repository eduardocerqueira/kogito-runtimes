package org.jbpm.process.instance.event;

import java.util.Map;

import org.kie.api.runtime.KieRuntime;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.kogito.internal.process.event.HumanTaskDeadlineEvent;
import org.kie.kogito.process.workitem.HumanTaskWorkItem;

public class HumanTaskDeadlineEventImpl extends ProcessEvent
        implements HumanTaskDeadlineEvent {
    private static final long serialVersionUID = 510l;

    private HumanTaskWorkItem workItem;
    private Map<String, Object> notification;
    private DeadlineType type;

    public HumanTaskDeadlineEventImpl(ProcessInstance instance, HumanTaskWorkItem workItem,
            Map<String, Object> notification, DeadlineType type, KieRuntime kruntime, String identity) {
        super(instance, kruntime, identity);
        this.workItem = workItem;
        this.notification = notification;
        this.type = type;
    }

    @Override
    public HumanTaskWorkItem getWorkItem() {
        return workItem;
    }

    @Override
    public Map<String, Object> getNotification() {
        return notification;
    }

    @Override
    public DeadlineType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "HumanTaskDeadlineEventImpl [workItem=" + workItem + ", notification=" + notification + ", type=" +
                type + "]";
    }
}
