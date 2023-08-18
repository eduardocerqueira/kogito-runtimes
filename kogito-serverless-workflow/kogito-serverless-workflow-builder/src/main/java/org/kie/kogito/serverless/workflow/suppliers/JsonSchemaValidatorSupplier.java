package org.kie.kogito.serverless.workflow.suppliers;

import java.util.function.Supplier;

import org.jbpm.compiler.canonical.descriptors.ExpressionUtils;
import org.kie.kogito.serverless.workflow.actions.JsonSchemaValidator;

import com.github.javaparser.ast.expr.Expression;

public class JsonSchemaValidatorSupplier extends JsonSchemaValidator implements Supplier<Expression> {

    private static final long serialVersionUID = 1L;

    public JsonSchemaValidatorSupplier(String schema, boolean failOnValidationErrors) {
        super(schema, failOnValidationErrors);
    }

    @Override
    public Expression get() {
        return ExpressionUtils.getObjectCreationExpr(JsonSchemaValidator.class, schemaRef, failOnValidationErrors);
    }
}
