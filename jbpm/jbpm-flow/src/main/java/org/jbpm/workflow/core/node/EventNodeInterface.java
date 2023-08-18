package org.jbpm.workflow.core.node;

import java.util.function.Function;

public interface EventNodeInterface {

    boolean acceptsEvent(String type, Object event, Function<String, Object> resolver);

    String getVariableName();

}
