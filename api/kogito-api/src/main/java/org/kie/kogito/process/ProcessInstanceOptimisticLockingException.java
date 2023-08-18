package org.kie.kogito.process;

public class ProcessInstanceOptimisticLockingException extends RuntimeException {

    private static final long serialVersionUID = 8031225233775014572L;

    private final String processInstanceId;

    public ProcessInstanceOptimisticLockingException(String processInstanceId) {
        super("Process instance with id '" + processInstanceId + "' updated or deleted by other request");
        this.processInstanceId = processInstanceId;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

}
