package org.jbpm.compiler.canonical;

import java.util.Map;

import org.jbpm.process.core.context.variable.VariableScope;
import org.jbpm.workflow.core.node.CompositeContextNode;
import org.kie.api.definition.process.Node;

import com.github.javaparser.ast.stmt.BlockStmt;

public abstract class AbstractCompositeNodeVisitor<T extends CompositeContextNode> extends AbstractNodeVisitor<T> {

    protected Map<Class<?>, AbstractNodeVisitor<? extends Node>> nodesVisitors;

    public AbstractCompositeNodeVisitor(Map<Class<?>, AbstractNodeVisitor<? extends Node>> nodesVisitors) {
        this.nodesVisitors = nodesVisitors;
    }

    protected <U extends Node> void visitNodes(String factoryField, U[] nodes, BlockStmt body, VariableScope variableScope, ProcessMetaData metadata) {
        for (U node : nodes) {
            AbstractNodeVisitor<U> visitor = (AbstractNodeVisitor<U>) nodesVisitors.get(node.getClass());
            if (visitor == null) {
                continue;
            }
            visitor.visitNode(factoryField, node, body, variableScope, metadata);
        }
    }

    protected String stripExpression(String expression) {
        if (expression.startsWith("#{")) {
            return expression.substring(2, expression.length() - 1);
        }
        return expression;
    }

}
