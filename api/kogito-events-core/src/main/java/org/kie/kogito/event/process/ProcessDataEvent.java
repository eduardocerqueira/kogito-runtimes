package org.kie.kogito.event.process;

import org.kie.kogito.event.AbstractDataEvent;

public class ProcessDataEvent<T> extends AbstractDataEvent<T> {

    public ProcessDataEvent() {
    }

    public ProcessDataEvent(T body) {
        setData(body);
    }

    public ProcessDataEvent(String type,
            String source,
            T body,
            String kogitoProcessInstanceId,
            String kogitoProcessInstanceVersion,
            String kogitoParentProcessInstanceId,
            String kogitoRootProcessInstanceId,
            String kogitoProcessId,
            String kogitoRootProcessId,
            String kogitoProcessInstanceState,
            String kogitoAddons,
            String kogitoProcessType,
            String kogitoReferenceId,
            String kogitoIdentity) {
        super(type,
                source,
                body,
                kogitoProcessInstanceId,
                kogitoRootProcessInstanceId,
                kogitoProcessId,
                kogitoRootProcessId,
                kogitoAddons,
                kogitoIdentity);
        setKogitoProcessInstanceVersion(kogitoProcessInstanceVersion);
        setKogitoParentProcessInstanceId(kogitoParentProcessInstanceId);
        setKogitoProcessInstanceState(kogitoProcessInstanceState);
        setKogitoReferenceId(kogitoReferenceId);
        setKogitoProcessType(kogitoProcessType);
    }

}
