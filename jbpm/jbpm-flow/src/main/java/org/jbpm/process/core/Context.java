package org.jbpm.process.core;

import java.io.Serializable;

public interface Context extends Serializable {

    String getType();

    long getId();

    void setId(long id);

    Context resolveContext(Object param);

    ContextContainer getContextContainer();

}
