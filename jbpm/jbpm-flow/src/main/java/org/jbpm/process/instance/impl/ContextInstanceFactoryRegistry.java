package org.jbpm.process.instance.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.jbpm.process.core.Context;
import org.jbpm.process.core.context.exception.CompensationScope;
import org.jbpm.process.core.context.exception.ExceptionScope;
import org.jbpm.process.core.context.swimlane.SwimlaneContext;
import org.jbpm.process.core.context.variable.VariableScope;
import org.jbpm.process.instance.ContextInstance;
import org.jbpm.process.instance.ContextInstanceContainer;
import org.jbpm.process.instance.ProcessInstance;
import org.jbpm.process.instance.context.AbstractContextInstance;
import org.jbpm.process.instance.context.exception.CompensationScopeInstance;
import org.jbpm.process.instance.context.exception.DefaultExceptionScopeInstance;
import org.jbpm.process.instance.context.swimlane.SwimlaneContextInstance;
import org.jbpm.process.instance.context.variable.VariableScopeInstance;

public class ContextInstanceFactoryRegistry {

    public static final ContextInstanceFactoryRegistry INSTANCE =
            new ContextInstanceFactoryRegistry();

    private Map<Class<? extends Context>, ContextInstanceFactory> registry;

    public ContextInstanceFactoryRegistry() {
        this.registry = new HashMap<>();
    }

    public void register(Class<? extends Context> cls,
            ContextInstanceFactory factory) {
        this.registry.put(cls, factory);
    }

    public ContextInstanceFactory getContextInstanceFactory(Context context) {
        Class<? extends Context> cls = context.getClass();
        // hard wired contexts:
        if (cls == VariableScope.class)
            return factoryOf(VariableScopeInstance::new);
        if (cls == ExceptionScope.class)
            return factoryOf(DefaultExceptionScopeInstance::new);
        if (cls == CompensationScope.class)
            return factoryOf(CompensationScopeInstance::new);
        if (cls == SwimlaneContext.class)
            return factoryOf(SwimlaneContextInstance::new);

        return this.registry.get(cls);
    }

    private static ContextInstanceFactory factoryOf(Supplier<? extends ContextInstance> supplier) {
        return (context, contextInstanceContainer, processInstance) -> getContextInstance(supplier,
                context,
                contextInstanceContainer,
                processInstance);
    }

    private static ContextInstance getContextInstance(Supplier<? extends ContextInstance> supplier,
            Context context,
            ContextInstanceContainer contextInstanceContainer,
            ProcessInstance processInstance) {
        ContextInstance result = contextInstanceContainer.getContextInstance(context.getType(), context.getId());
        if (result != null) {
            return result;
        }
        AbstractContextInstance contextInstance = (AbstractContextInstance) supplier.get();
        contextInstance.setProcessInstance(processInstance);
        contextInstance.setContextId(context.getId());
        contextInstance.setContextInstanceContainer(contextInstanceContainer);
        contextInstanceContainer.addContextInstance(context.getType(), contextInstance);
        return contextInstance;
    }

}
