package org.jbpm.workflow.instance.node;

import java.util.function.Function;

public interface EventNodeInstanceInterface {

    void signalEvent(String type, Object event, Function<String, Object> varResolver);

}
