package org.jbpm.process.instance;

import org.jbpm.process.core.Context;
import org.kie.kogito.internal.process.runtime.KogitoProcessInstance;

public interface ContextInstance {

    String getContextType();

    long getContextId();

    ContextInstanceContainer getContextInstanceContainer();

    Context getContext();

    KogitoProcessInstance getProcessInstance();

}