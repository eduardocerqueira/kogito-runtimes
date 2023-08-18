package org.jbpm.compiler.canonical;

import org.jbpm.process.core.context.variable.VariableScope;
import org.jbpm.ruleflow.core.factory.ThrowLinkNodeFactory;
import org.jbpm.workflow.core.node.ThrowLinkNode;

import com.github.javaparser.ast.expr.LongLiteralExpr;
import com.github.javaparser.ast.stmt.BlockStmt;

public class ThrowLinkNodeVisitor extends AbstractNodeVisitor<ThrowLinkNode> {

    @Override
    protected String getNodeKey() {
        return "throwLinkNode";
    }

    @Override
    public void visitNode(String factoryField,
            ThrowLinkNode node,
            BlockStmt body,
            VariableScope variableScope,
            ProcessMetaData metadata) {
        String nodeId = getNodeId(node);
        body.addStatement(getAssignedFactoryMethod(factoryField, ThrowLinkNodeFactory.class, nodeId,
                getNodeKey(), new LongLiteralExpr(node.getId())));
        body.addStatement(getDoneMethod(nodeId));
    }

}
