package org.jbpm.process.instance.event;

import org.drools.core.common.InternalKnowledgeRuntime;
import org.kie.kogito.signal.SignalManager;

public class DefaultSignalManagerFactory implements SignalManagerFactory {

    public SignalManager createSignalManager(InternalKnowledgeRuntime kruntime) {
        return new DefaultSignalManager(kruntime);
    }

}
