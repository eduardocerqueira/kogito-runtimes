package org.kie.kogito.jobs.api.event;

import java.util.Objects;

import org.kie.kogito.event.cloudevents.CloudEventExtensionConstants;

import io.cloudevents.CloudEvent;

@Deprecated
public abstract class AbstractProcessInstanceContextJobCloudEventBuilder<B extends AbstractProcessInstanceContextJobCloudEventBuilder<B, T, E>, T, E extends ProcessInstanceContextJobCloudEvent<T>>
        extends AbstractJobCloudEventBuilder<B, T, E> {

    protected AbstractProcessInstanceContextJobCloudEventBuilder(E current) {
        super(current);
    }

    public B processInstanceId(String processInstanceId) {
        event.setProcessInstanceId(processInstanceId);
        return cast();
    }

    public B processId(String processId) {
        event.setProcessId(processId);
        return cast();
    }

    public B rootProcessInstanceId(String rootProcessInstanceId) {
        event.setRootProcessInstanceId(rootProcessInstanceId);
        return cast();
    }

    public B rootProcessId(String rootProcessId) {
        event.setRootProcessId(rootProcessId);
        return cast();
    }

    public B kogitoAddons(String kogitoAddons) {
        event.setKogitoAddons(kogitoAddons);
        return cast();
    }

    public B withContextFrom(CloudEvent cloudEvent) {
        return processInstanceId(getExtensionAsString(cloudEvent, CloudEventExtensionConstants.PROCESS_INSTANCE_ID))
                .processId(getExtensionAsString(cloudEvent, CloudEventExtensionConstants.PROCESS_ID))
                .rootProcessInstanceId(getExtensionAsString(cloudEvent, CloudEventExtensionConstants.PROCESS_ROOT_PROCESS_INSTANCE_ID))
                .rootProcessId(getExtensionAsString(cloudEvent, CloudEventExtensionConstants.PROCESS_ROOT_PROCESS_ID))
                .kogitoAddons(getExtensionAsString(cloudEvent, CloudEventExtensionConstants.ADDONS));

    }

    private static String getExtensionAsString(CloudEvent cloudEvent, String extensionName) {
        return Objects.toString(cloudEvent.getExtension(extensionName));
    }
}
