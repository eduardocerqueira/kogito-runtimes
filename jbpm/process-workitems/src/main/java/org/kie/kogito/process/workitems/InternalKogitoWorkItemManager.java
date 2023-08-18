package org.kie.kogito.process.workitems;

import java.util.Map;
import java.util.Set;

import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemHandler;

public interface InternalKogitoWorkItemManager extends org.drools.core.process.WorkItemManager, org.kie.kogito.internal.process.runtime.KogitoWorkItemManager {

    void internalExecuteWorkItem(InternalKogitoWorkItem workItem);

    void internalAddWorkItem(InternalKogitoWorkItem workItem);

    void internalAbortWorkItem(String id);

    void internalCompleteWorkItem(InternalKogitoWorkItem workItem);

    InternalKogitoWorkItem getWorkItem(String id);

    void internalRemoveWorkItem(String id);

    void signalEvent(String type, Object event, String processInstanceId);

    void retryWorkItem(String workItemID, Map<String, Object> params);

    Set<org.drools.core.process.WorkItem> getWorkItems();

    @Override
    default void registerWorkItemHandler(String workItemName, WorkItemHandler handler) {
        registerWorkItemHandler(workItemName, (KogitoWorkItemHandler) handler);
    }

    @Override
    default void internalExecuteWorkItem(org.drools.core.process.WorkItem workItem) {
        throw new UnsupportedOperationException();
    }

    @Override
    default void internalAddWorkItem(org.drools.core.process.WorkItem workItem) {
        throw new UnsupportedOperationException();
    }

    @Override
    default void internalAbortWorkItem(long l) {
        throw new UnsupportedOperationException();
    }

    @Override
    default org.drools.core.process.WorkItem getWorkItem(long l) {
        throw new UnsupportedOperationException();
    }

    @Override
    default void retryWorkItem(Long aLong, Map<String, Object> map) {
        throw new UnsupportedOperationException();
    }

}
