package org.kie.kogito.serverless.workflow.suppliers;

import org.jbpm.compiler.canonical.ExpressionSupplier;
import org.jbpm.compiler.canonical.ProcessMetaData;
import org.jbpm.compiler.canonical.descriptors.ExpressionUtils;
import org.kie.kogito.internal.process.runtime.KogitoNode;
import org.kie.kogito.serverless.workflow.actions.SysoutAction;

import com.github.javaparser.ast.expr.Expression;

public class SysoutActionSupplier extends SysoutAction implements ExpressionSupplier {

    public SysoutActionSupplier(String lang, String expr, String inputVar, String logLevel) {
        super(lang, expr, inputVar, logLevel);
    }

    @Override
    public Expression get(KogitoNode node, ProcessMetaData metadata) {
        return ExpressionUtils.getObjectCreationExpr(SysoutAction.class, expr.lang(), expr.asString(), modelVar, logLevel, validExpr);
    }

}
