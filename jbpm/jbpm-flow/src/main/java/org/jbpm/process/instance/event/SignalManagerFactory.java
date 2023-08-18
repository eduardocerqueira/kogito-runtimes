package org.jbpm.process.instance.event;

import org.drools.core.common.InternalKnowledgeRuntime;
import org.kie.kogito.signal.SignalManager;

public interface SignalManagerFactory {

    SignalManager createSignalManager(InternalKnowledgeRuntime kruntime);

}
