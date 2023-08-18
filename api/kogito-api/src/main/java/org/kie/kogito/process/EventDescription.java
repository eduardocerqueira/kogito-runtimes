package org.kie.kogito.process;

import java.util.Map;

public interface EventDescription<T> {

    String getId();

    String getEvent();

    String getNodeId();

    String getNodeName();

    String getEventType();

    String getNodeInstanceId();

    String getProcessInstanceId();

    T getDataType();

    Map<String, String> getProperties();

}
