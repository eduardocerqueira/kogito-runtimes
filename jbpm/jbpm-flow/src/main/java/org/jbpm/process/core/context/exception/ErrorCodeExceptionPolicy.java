package org.jbpm.process.core.context.exception;

import org.kie.kogito.process.workitem.WorkItemExecutionException;

public class ErrorCodeExceptionPolicy extends AbstractRootCauseExceptionPolicy {

    @Override
    public boolean verify(String errorCode, Throwable exception) {
        return exception instanceof WorkItemExecutionException && getErrorCode(errorCode).equals(((WorkItemExecutionException) exception).getErrorCode());
    }

    private String getErrorCode(String errorCode) {
        String[] error = errorCode.split(":");
        return error[error.length - 1];
    }
}
