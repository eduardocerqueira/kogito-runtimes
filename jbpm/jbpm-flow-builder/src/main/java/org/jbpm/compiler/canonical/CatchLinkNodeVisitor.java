package org.jbpm.compiler.canonical;

import org.jbpm.process.core.context.variable.VariableScope;
import org.jbpm.ruleflow.core.factory.CatchLinkNodeFactory;
import org.jbpm.workflow.core.node.CatchLinkNode;

import com.github.javaparser.ast.expr.LongLiteralExpr;
import com.github.javaparser.ast.stmt.BlockStmt;

public class CatchLinkNodeVisitor extends AbstractNodeVisitor<CatchLinkNode> {

    @Override
    protected String getNodeKey() {
        return "catchLinkNode";
    }

    @Override
    public void visitNode(String factoryField,
            CatchLinkNode node,
            BlockStmt body,
            VariableScope variableScope,
            ProcessMetaData metadata) {
        String nodeId = getNodeId(node);
        body.addStatement(getAssignedFactoryMethod(factoryField, CatchLinkNodeFactory.class, nodeId,
                getNodeKey(), new LongLiteralExpr(node.getId())));
        body.addStatement(getDoneMethod(nodeId));
    }

}
