package org.jbpm.process.instance;

import java.util.List;

import org.jbpm.process.core.Context;
import org.jbpm.process.core.ContextContainer;

/**
 * 
 */
public interface ContextInstanceContainer {

    List<ContextInstance> getContextInstances(String contextId);

    void addContextInstance(String contextId, ContextInstance contextInstance);

    void removeContextInstance(String contextId, ContextInstance contextInstance);

    // TODO: does it make sense to have more than one contextInstance
    // with the same contextId (e.g. multiple variable scope instances
    // sharing the same context instance container?
    ContextInstance getContextInstance(String contextId, long id);

    ContextInstance getContextInstance(Context context);

    ContextContainer getContextContainer();

}
