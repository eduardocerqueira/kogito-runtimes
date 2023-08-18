package org.jbpm.workflow.instance.impl;

import org.jbpm.workflow.core.node.SubProcessNode;
import org.jbpm.workflow.instance.node.LambdaSubProcessNodeInstance;

public class CodegenNodeInstanceFactoryRegistry extends NodeInstanceFactoryRegistry {

    @Override
    protected NodeInstanceFactory get(Class<?> clazz) {
        if (SubProcessNode.class == clazz) {
            return factory(LambdaSubProcessNodeInstance::new);
        }
        return super.get(clazz);
    }
}
