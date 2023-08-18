package org.kie.kogito.serverless.workflow.suppliers;

import java.util.function.Supplier;

import org.jbpm.compiler.canonical.AbstractNodeVisitor;
import org.jbpm.compiler.canonical.ExpressionSupplier;
import org.jbpm.compiler.canonical.ProcessMetaData;
import org.jbpm.compiler.canonical.TriggerMetaData;
import org.jbpm.process.core.event.StaticMessageProducer;
import org.kie.kogito.event.impl.MessageProducer;
import org.kie.kogito.internal.process.runtime.KogitoNode;
import org.kie.kogito.serverless.workflow.actions.SWFProduceEventAction;
import org.kie.kogito.serverless.workflow.utils.ExpressionHandlerUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.NullLiteralExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;

import io.serverlessworkflow.api.Workflow;

import static com.github.javaparser.StaticJavaParser.parseClassOrInterfaceType;

public class ProduceEventActionSupplier extends SWFProduceEventAction implements ExpressionSupplier {

    private static final long serialVersionUID = 1L;

    public ProduceEventActionSupplier(Workflow workflow, String trigger, String varName, String data) {
        super(trigger, varName, new MessageProducerSupplier(trigger), workflow.getExpressionLang(), ExpressionHandlerUtils.replaceExpr(workflow, data));
    }

    @Override
    public Expression get(KogitoNode node, ProcessMetaData metadata) {
        return AbstractNodeVisitor.buildProducerAction(parseClassOrInterfaceType(SWFProduceEventAction.class.getCanonicalName()), TriggerMetaData.of(node), metadata)
                .addArgument(new StringLiteralExpr(exprLang))
                .addArgument(data != null ? new StringLiteralExpr().setString(data) : new NullLiteralExpr());
    }

    private static class MessageProducerSupplier implements Supplier<MessageProducer<JsonNode>> {

        private MessageProducer<JsonNode> producer;
        private final String trigger;

        public MessageProducerSupplier(String trigger) {
            this.trigger = trigger;
        }

        @Override
        public MessageProducer<JsonNode> get() {
            if (producer == null) {
                producer = new StaticMessageProducer<>(trigger);
            }
            return producer;
        }
    }
}
