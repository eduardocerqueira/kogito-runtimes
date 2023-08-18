package org.kie.kogito.event.process;

import java.util.Map;

public class ProcessInstanceDataEvent extends ProcessDataEvent<ProcessInstanceEventBody> {

    public ProcessInstanceDataEvent() {
    }

    public ProcessInstanceDataEvent(String source, String addons, String identity, Map<String, String> metaData, ProcessInstanceEventBody body) {
        super("ProcessInstanceEvent",
                source,
                body,
                metaData.get(ProcessInstanceEventBody.ID_META_DATA),
                metaData.get(ProcessInstanceEventBody.VERSION_META_DATA),
                metaData.get(ProcessInstanceEventBody.PARENT_ID_META_DATA),
                metaData.get(ProcessInstanceEventBody.ROOT_ID_META_DATA),
                metaData.get(ProcessInstanceEventBody.PROCESS_ID_META_DATA),
                metaData.get(ProcessInstanceEventBody.ROOT_PROCESS_ID_META_DATA),
                metaData.get(ProcessInstanceEventBody.STATE_META_DATA),
                addons,
                metaData.get(ProcessInstanceEventBody.PROCESS_TYPE_META_DATA),
                null,
                identity);
    }
}
