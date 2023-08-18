package org.kie.kogito.process.impl;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import org.kie.kogito.process.MutableProcessInstances;
import org.kie.kogito.process.ProcessInstance;
import org.kie.kogito.process.ProcessInstanceDuplicatedException;
import org.kie.kogito.process.ProcessInstanceReadMode;

class MapProcessInstances<T> implements MutableProcessInstances<T> {

    private final ConcurrentHashMap<String, ProcessInstance<T>> instances = new ConcurrentHashMap<>();

    @Override
    public Optional<ProcessInstance<T>> findById(String id, ProcessInstanceReadMode mode) {
        return Optional.ofNullable(instances.get(id));
    }

    @Override
    public void create(String id, ProcessInstance<T> instance) {
        if (isActive(instance)) {
            ProcessInstance<T> existing = instances.putIfAbsent(id, instance);
            if (existing != null) {
                throw new ProcessInstanceDuplicatedException(id);
            }
        }
    }

    @Override
    public void update(String id, ProcessInstance<T> instance) {
        if (isActive(instance)) {
            instances.put(id, instance);
        }
    }

    @Override
    public void remove(String id) {
        instances.remove(id);
    }

    @Override
    public boolean exists(String id) {
        return instances.containsKey(id);
    }

    @Override
    public Stream<ProcessInstance<T>> stream(ProcessInstanceReadMode mode) {
        return instances.values().stream();
    }
}
