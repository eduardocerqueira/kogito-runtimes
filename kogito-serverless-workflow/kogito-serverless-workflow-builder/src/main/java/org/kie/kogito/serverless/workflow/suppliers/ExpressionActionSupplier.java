package org.kie.kogito.serverless.workflow.suppliers;

import org.jbpm.compiler.canonical.ExpressionSupplier;
import org.jbpm.compiler.canonical.ProcessMetaData;
import org.jbpm.compiler.canonical.descriptors.ExpressionUtils;
import org.kie.kogito.internal.process.runtime.KogitoNode;
import org.kie.kogito.serverless.workflow.actions.ExpressionAction;
import org.kie.kogito.serverless.workflow.parser.ServerlessWorkflowParser;
import org.kie.kogito.serverless.workflow.utils.ExpressionHandlerUtils;

import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.ObjectCreationExpr;

import io.serverlessworkflow.api.Workflow;

public class ExpressionActionSupplier extends ExpressionAction implements ExpressionSupplier {

    public static Builder of(Workflow workflow, String expr) {
        return new Builder(workflow.getExpressionLang(), ExpressionHandlerUtils.replaceExpr(workflow, expr));
    }

    public static class Builder {

        private final String lang;
        private final String expr;
        private String inputVar = ServerlessWorkflowParser.DEFAULT_WORKFLOW_VAR;
        private String outputVar = ServerlessWorkflowParser.DEFAULT_WORKFLOW_VAR;

        private Builder(String lang, String expr) {
            this.lang = lang;
            this.expr = expr;
        }

        public Builder withVarNames(String varName) {
            this.inputVar = varName;
            this.outputVar = varName;
            return this;
        }

        public Builder withVarNames(String inputVar, String outputVar) {
            this.inputVar = inputVar;
            this.outputVar = outputVar;
            return this;
        }

        public ExpressionActionSupplier build() {
            return new ExpressionActionSupplier(lang, expr, inputVar, outputVar);
        }
    }

    private final ObjectCreationExpr expression;

    private ExpressionActionSupplier(String lang, String expr, String inputVar, String outputVar) {
        super(lang, expr, inputVar, outputVar);
        ExpressionUtils.checkValid(lang, expr);
        expression = ExpressionUtils.getObjectCreationExpr(ExpressionAction.class, lang, expr, inputVar, outputVar);
    }

    @Override
    public Expression get(KogitoNode node, ProcessMetaData metadata) {
        return expression;
    }
}
