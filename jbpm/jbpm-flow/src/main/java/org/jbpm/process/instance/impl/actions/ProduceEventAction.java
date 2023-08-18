package org.jbpm.process.instance.impl.actions;

import java.io.Serializable;
import java.util.function.Supplier;

import org.drools.core.common.InternalKnowledgeRuntime;
import org.jbpm.process.instance.InternalProcessRuntime;
import org.jbpm.process.instance.impl.Action;
import org.kie.kogito.event.impl.MessageProducer;
import org.kie.kogito.internal.process.runtime.KogitoProcessContext;
import org.kie.kogito.internal.process.runtime.KogitoProcessInstance;

public class ProduceEventAction<T> implements Action, Serializable {

    private static final long serialVersionUID = 1L;

    private final String varName;
    private final String triggerName;
    private final Supplier<MessageProducer<T>> supplier;

    public ProduceEventAction(String triggerName, String varName, Supplier<MessageProducer<T>> supplier) {
        this.triggerName = triggerName;
        this.varName = varName;
        this.supplier = supplier;
    }

    @Override
    public void execute(KogitoProcessContext context) throws Exception {
        Object object = context.getVariable(varName);
        KogitoProcessInstance pi = context.getProcessInstance();
        InternalKnowledgeRuntime runtime = (InternalKnowledgeRuntime) context.getKieRuntime();
        InternalProcessRuntime process = (InternalProcessRuntime) runtime.getProcessRuntime();
        process.getProcessEventSupport().fireOnMessage(pi, context.getNodeInstance(), runtime, triggerName, object);
        supplier.get().produce(pi, getObject(object, context));
    }

    protected T getObject(Object object, KogitoProcessContext context) {
        return (T) object;
    }
}
