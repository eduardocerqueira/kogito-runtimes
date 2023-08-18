package org.jbpm.workflow.core.node;

import java.util.List;

import org.jbpm.process.core.Context;
import org.jbpm.process.core.ContextContainer;
import org.jbpm.process.core.context.AbstractContext;
import org.jbpm.process.core.impl.ContextContainerImpl;

public class CompositeContextNode extends CompositeNode implements ContextContainer {

    private static final long serialVersionUID = 510l;

    private ContextContainer contextContainer = new ContextContainerImpl();

    public List<Context> getContexts(String contextType) {
        return this.contextContainer.getContexts(contextType);
    }

    public void addContext(Context context) {
        this.contextContainer.addContext(context);
        ((AbstractContext) context).setContextContainer(this);
    }

    public Context getContext(String contextType, long id) {
        return this.contextContainer.getContext(contextType, id);
    }

    public void setDefaultContext(Context context) {
        this.contextContainer.setDefaultContext(context);
        ((AbstractContext) context).setContextContainer(this);
    }

    public Context getDefaultContext(String contextType) {
        return this.contextContainer.getDefaultContext(contextType);
    }

    @Override
    public Context resolveContext(String contextId, Object param) {
        Context context = getDefaultContext(contextId);
        if (context != null) {
            context = context.resolveContext(param);
            if (context != null) {
                return context;
            }
        }
        return super.resolveContext(contextId, param);
    }

}
