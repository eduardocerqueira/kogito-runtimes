package org.kie.kogito.serverless.workflow.suppliers;

import org.jbpm.compiler.canonical.ExpressionSupplier;
import org.jbpm.compiler.canonical.ProcessMetaData;
import org.jbpm.compiler.canonical.descriptors.ExpressionUtils;
import org.kie.kogito.internal.process.runtime.KogitoNode;
import org.kie.kogito.serverless.workflow.actions.CollectorAction;

import com.github.javaparser.ast.expr.Expression;

public class CollectorActionSupplier extends CollectorAction implements ExpressionSupplier {

    private Expression expression;

    public CollectorActionSupplier(String lang, String expr, String inputVar, String outputVar) {
        super(lang, expr, inputVar, outputVar);
        ExpressionUtils.checkValid(lang, expr);
        expression = ExpressionUtils.getObjectCreationExpr(CollectorAction.class, lang, expr, inputVar, outputVar);
    }

    @Override
    public Expression get(KogitoNode node, ProcessMetaData metadata) {
        return expression;
    }

}
