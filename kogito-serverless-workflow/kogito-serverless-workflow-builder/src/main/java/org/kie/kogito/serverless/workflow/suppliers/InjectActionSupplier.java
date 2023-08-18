package org.kie.kogito.serverless.workflow.suppliers;

import org.jbpm.compiler.canonical.ExpressionSupplier;
import org.jbpm.compiler.canonical.ProcessMetaData;
import org.jbpm.compiler.canonical.descriptors.ExpressionUtils;
import org.kie.kogito.internal.process.runtime.KogitoNode;
import org.kie.kogito.jackson.utils.JsonObjectUtils;
import org.kie.kogito.serverless.workflow.actions.InjectAction;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.javaparser.ast.expr.Expression;

public class InjectActionSupplier extends InjectAction implements ExpressionSupplier {

    public InjectActionSupplier(JsonNode node) {
        super(node);
    }

    @Override
    public Expression get(KogitoNode kogitoNode, ProcessMetaData metadata) {
        try {
            return ExpressionUtils.getObjectCreationExpr(InjectAction.class, JsonObjectUtils.toString(this.node));
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }
}
