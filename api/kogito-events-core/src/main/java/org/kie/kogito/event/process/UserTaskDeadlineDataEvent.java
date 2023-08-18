package org.kie.kogito.event.process;

import org.kie.kogito.event.AbstractDataEvent;

public class UserTaskDeadlineDataEvent extends AbstractDataEvent<UserTaskDeadlineEventBody> {

    public UserTaskDeadlineDataEvent() {
    }

    public UserTaskDeadlineDataEvent(String type, String source, String addons, String identity,
            UserTaskDeadlineEventBody body, String processInstanceId,
            String rootProcessInstanceId, String processId, String rootProcessId) {
        super(type,
                source,
                body,
                processInstanceId,
                rootProcessInstanceId,
                processId,
                rootProcessId,
                addons,
                identity);
    }

}
