package org.kie.kogito.tracing.decision.event.evaluate;

import org.kie.dmn.api.core.event.AfterEvaluateContextEntryEvent;
import org.kie.dmn.api.core.event.BeforeEvaluateContextEntryEvent;

import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

public class EvaluateContextEntryResult {

    @JsonInclude(NON_NULL)
    private String variableId;
    private String variableName;
    private String expressionId;
    private Object expressionResult;

    public EvaluateContextEntryResult(String variableId, String variableName, String expressionId, Object expressionResult) {
        this.variableId = variableId;
        this.variableName = variableName;
        this.expressionId = expressionId;
        this.expressionResult = expressionResult;
    }

    private EvaluateContextEntryResult() {
    }

    public String getVariableId() {
        return variableId;
    }

    public String getVariableName() {
        return variableName;
    }

    public String getExpressionId() {
        return expressionId;
    }

    public Object getExpressionResult() {
        return expressionResult;
    }

    public static EvaluateContextEntryResult from(BeforeEvaluateContextEntryEvent event) {
        return new EvaluateContextEntryResult(event.getVariableId(), event.getVariableName(), event.getExpressionId(), null);
    }

    public static EvaluateContextEntryResult from(AfterEvaluateContextEntryEvent event) {
        return new EvaluateContextEntryResult(event.getVariableId(), event.getVariableName(), event.getExpressionId(), event.getExpressionResult());
    }
}
