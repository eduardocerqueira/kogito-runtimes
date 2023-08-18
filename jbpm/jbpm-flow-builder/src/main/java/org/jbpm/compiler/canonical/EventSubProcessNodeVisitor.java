package org.jbpm.compiler.canonical;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Stream;

import org.jbpm.process.core.context.variable.VariableScope;
import org.jbpm.ruleflow.core.factory.EventSubProcessNodeFactory;
import org.jbpm.workflow.core.node.EventSubProcessNode;
import org.kie.api.definition.process.Node;

import com.github.javaparser.ast.expr.BooleanLiteralExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;

import static org.jbpm.ruleflow.core.factory.EventSubProcessNodeFactory.METHOD_EVENT;
import static org.jbpm.ruleflow.core.factory.EventSubProcessNodeFactory.METHOD_KEEP_ACTIVE;

public class EventSubProcessNodeVisitor extends CompositeContextNodeVisitor<EventSubProcessNode> {

    public EventSubProcessNodeVisitor(Map<Class<?>, AbstractNodeVisitor<? extends Node>> nodesVisitors) {
        super(nodesVisitors);
    }

    @Override
    protected Class<?> factoryClass() {
        return EventSubProcessNodeFactory.class;
    }

    @Override
    protected String getNodeKey() {
        return "eventSubProcessNode";
    }

    @Override
    protected String getDefaultName() {
        return "EventSubProcess";
    }

    @Override
    public Stream<MethodCallExpr> visitCustomFields(EventSubProcessNode node, VariableScope variableScope) {
        Collection<MethodCallExpr> methods = new ArrayList<>();
        methods.add(getFactoryMethod(getNodeId(node), METHOD_KEEP_ACTIVE, new BooleanLiteralExpr(node.isKeepActive())));
        node.getEvents()
                .forEach(e -> methods.add(getFactoryMethod(getNodeId(node), METHOD_EVENT, new StringLiteralExpr(e))));
        return methods.stream();
    }
}
