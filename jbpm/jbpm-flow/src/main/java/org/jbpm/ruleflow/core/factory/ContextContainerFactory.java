package org.jbpm.ruleflow.core.factory;

import org.jbpm.process.core.Context;
import org.jbpm.process.core.ContextContainer;

public interface ContextContainerFactory<T extends NodeFactory<T, ?>> {
    ContextContainer getContextNode();

    default T context(Context context) {
        getContextNode().addContext(context);
        return (T) this;
    }

    default T defaultContext(Context context) {
        getContextNode().setDefaultContext(context);
        return (T) this;
    }
}
