package org.jbpm.ruleflow.core.factory;

import java.util.function.Predicate;

import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.workflow.core.NodeContainer;
import org.jbpm.workflow.core.node.DynamicNode;
import org.kie.api.runtime.process.ProcessContext;

public class DynamicNodeFactory<T extends RuleFlowNodeContainerFactory<T, ?>> extends AbstractCompositeNodeFactory<DynamicNodeFactory<T>, T> {

    public static final String METHOD_LANGUAGE = "language";
    public static final String METHOD_ACTIVATION_EXPRESSION = "activationExpression";
    public static final String METHOD_COMPLETION_EXPRESSION = "completionExpression";

    public DynamicNodeFactory(T nodeContainerFactory, NodeContainer nodeContainer, long id) {
        super(nodeContainerFactory, nodeContainer, new DynamicNode(), id);
    }

    protected DynamicNode getDynamicNode() {
        return (DynamicNode) node;
    }

    public DynamicNodeFactory<T> language(String language) {
        getDynamicNode().setLanguage(language);
        return this;
    }

    public DynamicNodeFactory<T> activationExpression(Predicate<ProcessContext> activationExpression) {
        getDynamicNode().setActivationExpression(activationExpression);
        return this;
    }

    public DynamicNodeFactory<T> completionExpression(Predicate<ProcessContext> completionExpression) {
        getDynamicNode().setCompletionExpression(completionExpression);
        return this;
    }
}
