package org.jbpm.process.instance;

import java.util.Collection;

import org.kie.kogito.internal.process.runtime.KogitoProcessInstance;

public interface ProcessInstanceManager {

    KogitoProcessInstance getProcessInstance(String id);

    KogitoProcessInstance getProcessInstance(String id, boolean readOnly);

    Collection<KogitoProcessInstance> getProcessInstances();

    void addProcessInstance(KogitoProcessInstance processInstance);

    void internalAddProcessInstance(KogitoProcessInstance processInstance);

    void removeProcessInstance(KogitoProcessInstance processInstance);

    void internalRemoveProcessInstance(KogitoProcessInstance processInstance);

    void clearProcessInstances();

    void clearProcessInstancesState();

    void setLock(boolean lock);

}
