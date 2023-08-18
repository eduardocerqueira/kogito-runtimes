package org.jbpm.compiler.canonical;

import org.jbpm.process.core.context.variable.VariableScope;
import org.jbpm.ruleflow.core.factory.JoinFactory;
import org.jbpm.workflow.core.node.Join;

import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.LongLiteralExpr;
import com.github.javaparser.ast.stmt.BlockStmt;

import static org.jbpm.ruleflow.core.factory.JoinFactory.METHOD_TYPE;

public class JoinNodeVisitor extends AbstractNodeVisitor<Join> {

    @Override
    protected String getNodeKey() {
        return "joinNode";
    }

    @Override
    public void visitNode(String factoryField, Join node, BlockStmt body, VariableScope variableScope, ProcessMetaData metadata) {
        body.addStatement(getAssignedFactoryMethod(factoryField, JoinFactory.class, getNodeId(node), getNodeKey(), new LongLiteralExpr(node.getId())));
        body.addStatement(getNameMethod(node, "Join"));
        body.addStatement(getFactoryMethod(getNodeId(node), METHOD_TYPE, new IntegerLiteralExpr(node.getType())));

        visitMetaData(node.getMetaData(), body, getNodeId(node));
        body.addStatement(getDoneMethod(getNodeId(node)));
    }
}
