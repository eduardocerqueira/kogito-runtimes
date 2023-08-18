package org.jbpm.compiler.canonical;

import org.jbpm.process.core.context.variable.VariableScope;
import org.jbpm.ruleflow.core.factory.FaultNodeFactory;
import org.jbpm.workflow.core.node.FaultNode;

import com.github.javaparser.ast.expr.BooleanLiteralExpr;
import com.github.javaparser.ast.expr.LongLiteralExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.stmt.BlockStmt;

import static org.jbpm.ruleflow.core.factory.FaultNodeFactory.METHOD_FAULT_NAME;
import static org.jbpm.ruleflow.core.factory.FaultNodeFactory.METHOD_FAULT_VARIABLE;
import static org.jbpm.ruleflow.core.factory.FaultNodeFactory.METHOD_TERMINATE_PARENT;

public class FaultNodeVisitor extends AbstractNodeVisitor<FaultNode> {

    @Override
    protected String getNodeKey() {
        return "faultNode";
    }

    @Override
    public void visitNode(String factoryField, FaultNode node, BlockStmt body, VariableScope variableScope, ProcessMetaData metadata) {
        body.addStatement(getAssignedFactoryMethod(factoryField, FaultNodeFactory.class, getNodeId(node), getNodeKey(), new LongLiteralExpr(node.getId())))
                .addStatement(getNameMethod(node, "Error"));
        if (node.getFaultVariable() != null) {
            body.addStatement(getFactoryMethod(getNodeId(node), METHOD_FAULT_VARIABLE, new StringLiteralExpr(node.getFaultVariable())));
        }
        if (node.getFaultName() != null) {
            body.addStatement(getFactoryMethod(getNodeId(node), METHOD_FAULT_NAME, new StringLiteralExpr(node.getFaultName())));
        }

        body.addStatement(getFactoryMethod(getNodeId(node), METHOD_TERMINATE_PARENT, new BooleanLiteralExpr(node.isTerminateParent())));
        addNodeMappings(node, body, getNodeId(node));
        visitMetaData(node.getMetaData(), body, getNodeId(node));
        body.addStatement(getDoneMethod(getNodeId(node)));
    }
}
