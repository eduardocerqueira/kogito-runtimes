package org.jbpm.process.core.context.exclusive;

import org.jbpm.process.core.Context;
import org.jbpm.process.core.context.AbstractContext;

public class ExclusiveGroup extends AbstractContext {

    private static final long serialVersionUID = 510l;

    public static final String EXCLUSIVE_GROUP = "ExclusiveGroup";

    public String getType() {
        return EXCLUSIVE_GROUP;
    }

    public Context resolveContext(Object param) {
        return null;
    }

}
