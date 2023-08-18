package org.kie.kogito.process;

/**
 * Thrown when there is problems encountered during process instance execution.
 * Usually caused by one of the node instances not able to perform desired action.
 * 
 */
public class ProcessInstanceExecutionException extends RuntimeException {

    private static final long serialVersionUID = 8031225233775014572L;

    private final String processInstanceId;
    private final String failedNodeId;
    private final String errorMessage;

    public ProcessInstanceExecutionException(String processInstanceId, String failedNodeId, String errorMessage) {
        this(processInstanceId, failedNodeId, errorMessage, null);
    }

    public ProcessInstanceExecutionException(String processInstanceId, String failedNodeId, String errorMessage, Throwable rootCause) {
        super("Process instance with id " + processInstanceId + " failed becuase of " + errorMessage, rootCause);
        this.processInstanceId = processInstanceId;
        this.failedNodeId = failedNodeId;
        this.errorMessage = errorMessage;
    }

    /**
     * Returns process instance id of the instance that failed.
     * 
     * @return process instance id
     */
    public String getProcessInstanceId() {
        return processInstanceId;
    }

    /**
     * Returns node definition id of the node instance that failed to execute
     * 
     * @return node definition id
     */
    public String getFailedNodeId() {
        return failedNodeId;
    }

    /**
     * Returns error message associated with this failure. Usually will consists of
     * error id, fully qualified class name of the root cause exception and error message
     * 
     * @return error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

}
