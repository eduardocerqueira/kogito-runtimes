package org.kie.kogito.serverless.workflow.suppliers;

import org.jbpm.compiler.canonical.ExpressionSupplier;
import org.jbpm.compiler.canonical.ProcessMetaData;
import org.jbpm.compiler.canonical.descriptors.ExpressionUtils;
import org.kie.kogito.internal.process.runtime.KogitoNode;
import org.kie.kogito.serverless.workflow.actions.MergeAction;

import com.github.javaparser.ast.expr.Expression;

public class MergeActionSupplier extends MergeAction implements ExpressionSupplier {

    private Expression expression;

    public MergeActionSupplier(String inputName, String outputName) {
        super(inputName, outputName);
        this.expression = ExpressionUtils.getObjectCreationExpr(MergeAction.class, inputName, outputName);

    }

    @Override
    public Expression get(KogitoNode node, ProcessMetaData metadata) {
        return expression;

    }
}
