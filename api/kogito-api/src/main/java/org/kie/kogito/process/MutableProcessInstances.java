package org.kie.kogito.process;

public interface MutableProcessInstances<T> extends ProcessInstances<T> {

    boolean exists(String id);

    void create(String id, ProcessInstance<T> instance);

    void update(String id, ProcessInstance<T> instance);

    void remove(String id);

    default boolean isActive(ProcessInstance<T> instance) {
        return instance.status() == ProcessInstance.STATE_ACTIVE || instance.status() == ProcessInstance.STATE_ERROR;
    }

    default boolean lock() {
        return false;
    }
}
