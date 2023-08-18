package org.jbpm.compiler.canonical;

import org.jbpm.process.core.context.variable.VariableScope;
import org.jbpm.ruleflow.core.factory.MilestoneNodeFactory;
import org.jbpm.workflow.core.node.MilestoneNode;

import com.github.javaparser.ast.expr.LongLiteralExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.stmt.BlockStmt;

import static org.jbpm.ruleflow.core.factory.MilestoneNodeFactory.METHOD_CONDITION;

public class MilestoneNodeVisitor extends AbstractNodeVisitor<MilestoneNode> {

    @Override
    protected String getNodeKey() {
        return "milestoneNode";
    }

    @Override
    public void visitNode(String factoryField, MilestoneNode node, BlockStmt body, VariableScope variableScope, ProcessMetaData metadata) {
        body.addStatement(getAssignedFactoryMethod(factoryField, MilestoneNodeFactory.class, getNodeId(node), getNodeKey(), new LongLiteralExpr(node.getId())))
                .addStatement(getNameMethod(node, "Milestone"));
        if (node.getCondition() != null && !node.getCondition().trim().isEmpty()) {
            body.addStatement(getConditionStatement(node, variableScope));
        }
        body.addStatement(getDoneMethod(getNodeId(node)));
        visitMetaData(node.getMetaData(), body, getNodeId(node));
    }

    private MethodCallExpr getConditionStatement(MilestoneNode node, VariableScope scope) {
        return getFactoryMethod(getNodeId(node), METHOD_CONDITION, createLambdaExpr(node.getCondition(), scope));
    }

}
