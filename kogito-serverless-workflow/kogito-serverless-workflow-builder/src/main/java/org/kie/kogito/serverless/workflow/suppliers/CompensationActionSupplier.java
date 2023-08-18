package org.kie.kogito.serverless.workflow.suppliers;

import org.jbpm.compiler.canonical.ExpressionSupplier;
import org.jbpm.compiler.canonical.ProcessMetaData;
import org.jbpm.compiler.canonical.descriptors.ExpressionUtils;
import org.jbpm.process.instance.impl.actions.ProcessInstanceCompensationAction;
import org.kie.kogito.internal.process.runtime.KogitoNode;

import com.github.javaparser.ast.expr.Expression;

public class CompensationActionSupplier extends ProcessInstanceCompensationAction implements ExpressionSupplier {

    private static final long serialVersionUID = 1L;

    public CompensationActionSupplier(String activityRef) {
        super(activityRef);
    }

    @Override
    public Expression get(KogitoNode node, ProcessMetaData metadata) {
        return ExpressionUtils.getObjectCreationExpr(ProcessInstanceCompensationAction.class, getActivityRef());
    }
}
