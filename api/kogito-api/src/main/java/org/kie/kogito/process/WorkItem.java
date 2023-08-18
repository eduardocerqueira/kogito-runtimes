package org.kie.kogito.process;

import java.util.Map;

public interface WorkItem {

    String getId();

    default String getNodeId() {
        throw new UnsupportedOperationException();
    }

    String getNodeInstanceId();

    String getName();

    int getState();

    String getPhase();

    String getPhaseStatus();

    Map<String, Object> getParameters();

    Map<String, Object> getResults();
}
