package org.jbpm.process.core.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.process.core.Context;
import org.jbpm.process.core.ContextContainer;

/**
 * 
 */
public class ContextContainerImpl implements Serializable, ContextContainer {

    private static final long serialVersionUID = 510l;

    private Map<String, Context> defaultContexts = new HashMap<>();
    private Map<String, List<Context>> subContexts = new HashMap<>();
    private long lastContextId;

    public List<Context> getContexts(String contextType) {
        return this.subContexts.get(contextType);
    }

    public void addContext(Context context) {
        List<Context> list = this.subContexts.get(context.getType());
        if (list == null) {
            list = new ArrayList<>();
            this.subContexts.put(context.getType(), list);
        }
        if (!list.contains(context)) {
            list.add(context);
            context.setId(++lastContextId);
        }
    }

    public Context getContext(String contextType, long id) {
        List<Context> list = this.subContexts.get(contextType);
        if (list != null) {
            for (Context context : list) {
                if (context.getId() == id) {
                    return context;
                }
            }
        }
        return null;
    }

    public void setDefaultContext(Context context) {
        this.defaultContexts.put(context.getType(), context);
    }

    public Context getDefaultContext(String contextType) {
        return defaultContexts.get(contextType);
    }

}
