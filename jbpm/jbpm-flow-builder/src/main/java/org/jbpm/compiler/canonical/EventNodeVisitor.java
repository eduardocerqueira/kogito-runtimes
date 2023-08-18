package org.jbpm.compiler.canonical;

import java.text.MessageFormat;

import org.jbpm.process.core.context.variable.Variable;
import org.jbpm.process.core.context.variable.VariableScope;
import org.jbpm.ruleflow.core.factory.EventNodeFactory;
import org.jbpm.workflow.core.node.EventNode;

import com.github.javaparser.ast.expr.LongLiteralExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.stmt.BlockStmt;

import static org.jbpm.ruleflow.core.Metadata.EVENT_TYPE;
import static org.jbpm.ruleflow.core.Metadata.EVENT_TYPE_MESSAGE;
import static org.jbpm.ruleflow.core.Metadata.EVENT_TYPE_SIGNAL;
import static org.jbpm.ruleflow.core.factory.EventNodeFactory.METHOD_EVENT_TYPE;
import static org.jbpm.ruleflow.core.factory.EventNodeFactory.METHOD_INPUT_VARIABLE_NAME;
import static org.jbpm.ruleflow.core.factory.EventNodeFactory.METHOD_VARIABLE_NAME;

public class EventNodeVisitor extends AbstractNodeVisitor<EventNode> {

    @Override
    protected String getNodeKey() {
        return "eventNode";
    }

    @Override
    public void visitNode(String factoryField, EventNode node, BlockStmt body, VariableScope variableScope, ProcessMetaData metadata) {
        body.addStatement(getAssignedFactoryMethod(factoryField, EventNodeFactory.class, getNodeId(node), getNodeKey(), new LongLiteralExpr(node.getId())))
                .addStatement(getNameMethod(node, "Event"))
                .addStatement(getFactoryMethod(getNodeId(node), METHOD_EVENT_TYPE, new StringLiteralExpr(node.getType())));

        Variable variable = null;
        if (node.getVariableName() != null) {
            body.addStatement(getFactoryMethod(getNodeId(node), METHOD_VARIABLE_NAME, new StringLiteralExpr(node.getVariableName())));
            variable = variableScope.findVariable(node.getVariableName());
        }
        if (node.getInputVariableName() != null) {
            body.addStatement(getFactoryMethod(getNodeId(node), METHOD_INPUT_VARIABLE_NAME, new StringLiteralExpr(node.getInputVariableName())));
        }

        if (EVENT_TYPE_SIGNAL.equals(node.getMetaData(EVENT_TYPE))) {
            metadata.addSignal(node.getType(), variable != null ? variable.getType().getStringType() : null);
        } else if (EVENT_TYPE_MESSAGE.equals(node.getMetaData(EVENT_TYPE))) {
            try {
                metadata.addTrigger(TriggerMetaData.of(node, node.getVariableName()));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(
                        MessageFormat.format(
                                "Invalid parameters for event node \"{0}\": {1}",
                                node.getName(),
                                e.getMessage()),
                        e);
            }
        }
        addNodeMappings(node, body, getNodeId(node));
        visitMetaData(node.getMetaData(), body, getNodeId(node));
        body.addStatement(getDoneMethod(getNodeId(node)));
    }

}
