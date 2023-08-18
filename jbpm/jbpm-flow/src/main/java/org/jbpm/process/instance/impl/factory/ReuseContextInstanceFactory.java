package org.jbpm.process.instance.impl.factory;

import org.jbpm.process.core.Context;
import org.jbpm.process.instance.ContextInstance;
import org.jbpm.process.instance.ContextInstanceContainer;
import org.jbpm.process.instance.ProcessInstance;
import org.jbpm.process.instance.context.AbstractContextInstance;
import org.jbpm.process.instance.impl.ContextInstanceFactory;

public class ReuseContextInstanceFactory implements ContextInstanceFactory {

    private final Class<? extends ContextInstance> cls;

    public ReuseContextInstanceFactory(Class<? extends ContextInstance> cls) {
        this.cls = cls;
    }

    public ContextInstance getContextInstance(Context context, ContextInstanceContainer contextInstanceContainer, ProcessInstance processInstance) {
        ContextInstance result = contextInstanceContainer.getContextInstance(context.getType(), context.getId());
        if (result != null) {
            return result;
        }
        try {
            AbstractContextInstance contextInstance = (AbstractContextInstance) cls.newInstance();
            contextInstance.setProcessInstance(processInstance);
            contextInstance.setContextId(context.getId());
            contextInstance.setContextInstanceContainer(contextInstanceContainer);
            contextInstanceContainer.addContextInstance(context.getType(), contextInstance);
            return contextInstance;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to instantiate context '"
                    + this.cls.getName() + "': " + e.getMessage(), e);
        }
    }

}
