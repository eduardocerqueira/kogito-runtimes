package org.kie.kogito.event.process;

import java.util.Map;
import java.util.Set;

import org.kie.kogito.event.AbstractDataEvent;
import org.kie.kogito.event.cloudevents.CloudEventExtensionConstants;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserTaskInstanceDataEvent extends AbstractDataEvent<UserTaskInstanceEventBody> {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty(CloudEventExtensionConstants.PROCESS_USER_TASK_INSTANCE_ID)
    private String kogitoUserTaskinstanceId;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty(CloudEventExtensionConstants.PROCESS_USER_TASK_INSTANCE_STATE)
    private String kogitoUserTaskinstanceState;

    private static final Set<String> INTERNAL_EXTENSION_ATTRIBUTES = Set.of(
            CloudEventExtensionConstants.PROCESS_USER_TASK_INSTANCE_ID,
            CloudEventExtensionConstants.PROCESS_USER_TASK_INSTANCE_STATE);

    public UserTaskInstanceDataEvent() {
    }

    public UserTaskInstanceDataEvent(String source, String addons, String identity, Map<String, String> metaData, UserTaskInstanceEventBody body) {
        super("UserTaskInstanceEvent",
                source,
                body,
                metaData.get(ProcessInstanceEventBody.ID_META_DATA),
                metaData.get(ProcessInstanceEventBody.ROOT_ID_META_DATA),
                metaData.get(ProcessInstanceEventBody.PROCESS_ID_META_DATA),
                metaData.get(ProcessInstanceEventBody.ROOT_PROCESS_ID_META_DATA),
                addons,
                identity);
        addExtensionAttribute(CloudEventExtensionConstants.PROCESS_USER_TASK_INSTANCE_STATE, metaData.get(UserTaskInstanceEventBody.UT_STATE_META_DATA));
        addExtensionAttribute(CloudEventExtensionConstants.PROCESS_USER_TASK_INSTANCE_ID, metaData.get(metaData.get(UserTaskInstanceEventBody.UT_ID_META_DATA)));
    }

    public String getKogitoUserTaskinstanceId() {
        return kogitoUserTaskinstanceId;
    }

    public String getKogitoUserTaskinstanceState() {
        return kogitoUserTaskinstanceState;
    }

    public void setKogitoUserTaskinstanceId(String kogitoUserTaskinstanceId) {
        addExtensionAttribute(CloudEventExtensionConstants.PROCESS_USER_TASK_INSTANCE_ID, kogitoUserTaskinstanceId);
    }

    public void setKogitoUserTaskinstanceState(String kogitoUserTaskinstanceState) {
        addExtensionAttribute(CloudEventExtensionConstants.PROCESS_USER_TASK_INSTANCE_STATE, kogitoUserTaskinstanceState);
    }

    @Override
    @JsonAnySetter
    public void addExtensionAttribute(String name, Object value) {
        if (value != null) {
            switch (name) {
                case CloudEventExtensionConstants.PROCESS_USER_TASK_INSTANCE_STATE:
                    this.kogitoUserTaskinstanceState = (String) value;
                    break;
                case CloudEventExtensionConstants.PROCESS_USER_TASK_INSTANCE_ID:
                    this.kogitoUserTaskinstanceId = (String) value;
                    break;
            }
            super.addExtensionAttribute(name, value);
        }
    }

    @Override
    protected boolean isInternalAttribute(String name) {
        return INTERNAL_EXTENSION_ATTRIBUTES.contains(name) || super.isInternalAttribute(name);
    }
}
