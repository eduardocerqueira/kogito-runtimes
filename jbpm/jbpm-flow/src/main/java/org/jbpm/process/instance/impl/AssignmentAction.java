package org.jbpm.process.instance.impl;

import java.util.function.Function;

public interface AssignmentAction {

    void execute(Function<String, Object> sourceResolver, Function<String, Object> targetResolver, AssignmentProducer producer) throws Exception;

}
