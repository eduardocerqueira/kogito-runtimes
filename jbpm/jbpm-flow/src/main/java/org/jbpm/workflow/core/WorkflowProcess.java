package org.jbpm.workflow.core;

import java.util.Optional;
import java.util.function.BiFunction;

import org.jbpm.process.core.Process;
import org.jbpm.process.instance.ProcessInstance;
import org.kie.kogito.internal.process.runtime.KogitoWorkflowProcess;

/**
 * Represents a RuleFlow process.
 * 
 */
public interface WorkflowProcess extends KogitoWorkflowProcess, Process, NodeContainer {

    int PROCESS_TYPE = 1;
    int CASE_TYPE = 2;

    /**
     * Returns whether this process will automatically complete if it
     * contains no active node instances anymore
     * 
     * @return the names of the globals of this RuleFlow process
     */
    boolean isAutoComplete();

    boolean isDynamic();

    Integer getProcessType();

    void setExpressionEvaluator(BiFunction<String, ProcessInstance, String> expressionEvaluator);

    String evaluateExpression(String metaData, ProcessInstance processInstance);

    Optional<WorkflowModelValidator> getInputValidator();

    void setInputValidator(WorkflowModelValidator validator);

    Optional<WorkflowModelValidator> getOutputValidator();

    void setOutputValidator(WorkflowModelValidator validator);

}
