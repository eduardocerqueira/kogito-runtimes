package org.jbpm.process.instance.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.jbpm.process.instance.ProcessInstanceManager;
import org.kie.kogito.internal.process.runtime.KogitoProcessInstance;

public class DefaultProcessInstanceManager implements ProcessInstanceManager {

    private Map<String, KogitoProcessInstance> processInstances = new ConcurrentHashMap<>();

    private boolean lock = false;

    public void addProcessInstance(KogitoProcessInstance processInstance) {
        if (Objects.isNull(processInstance.getStringId())) {
            ((org.jbpm.process.instance.ProcessInstance) processInstance).setId(UUID.randomUUID().toString());
        }
        internalAddProcessInstance(processInstance);
    }

    public void internalAddProcessInstance(KogitoProcessInstance processInstance) {
        if (lock) {
            processInstances.put(processInstance.getStringId() + "_" + Thread.currentThread().getId(), processInstance);
        } else {
            processInstances.put(processInstance.getStringId(), processInstance);
        }
    }

    public Collection<KogitoProcessInstance> getProcessInstances() {
        return Collections.unmodifiableCollection(processInstances.values());
    }

    public KogitoProcessInstance getProcessInstance(String id) {
        if (lock) {
            return processInstances.get(id + "_" + Thread.currentThread().getId());
        } else {
            return processInstances.get(id);
        }
    }

    public KogitoProcessInstance getProcessInstance(String id, boolean readOnly) {
        if (lock) {
            return processInstances.get(id + "_" + Thread.currentThread().getId());
        } else {
            return processInstances.get(id);
        }
    }

    public void removeProcessInstance(KogitoProcessInstance processInstance) {
        internalRemoveProcessInstance(processInstance);
    }

    public void internalRemoveProcessInstance(KogitoProcessInstance processInstance) {
        if (lock) {
            processInstances.remove(processInstance.getStringId() + "_" + Thread.currentThread().getId());
        } else {
            processInstances.remove(processInstance.getStringId());
        }
    }

    public void clearProcessInstances() {
        processInstances.clear();
    }

    public void clearProcessInstancesState() {

    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }
}
