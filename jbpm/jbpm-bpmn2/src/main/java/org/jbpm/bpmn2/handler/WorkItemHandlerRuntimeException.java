package org.jbpm.bpmn2.handler;

import java.util.*;

import org.kie.api.runtime.process.WorkItemHandler;

/**
 * This exception provides extra information about the WorkItemHandler operation called to catchers of this exception.
 * It is only meant to be thrown from a {@link WorkItemHandler} instance method.
 */
public class WorkItemHandlerRuntimeException extends RuntimeException {

    /** Generated serial version uid */
    private static final long serialVersionUID = 1217036861831832336L;

    public static final String WORKITEMHANDLERTYPE = "workItemHandlerType";

    private final Map<String, Object> info;

    public WorkItemHandlerRuntimeException(Throwable cause, String message) {
        this(cause, message, Collections.emptyMap());
    }

    public WorkItemHandlerRuntimeException(Throwable cause) {
        this(cause, Collections.emptyMap());
    }

    public WorkItemHandlerRuntimeException(Throwable cause, Map<String, Object> info) {
        super(cause);
        this.info = info;
    }

    public WorkItemHandlerRuntimeException(Throwable cause, String message, Map<String, Object> info) {
        super(message, cause);
        this.info = info;
    }

    public Map<String, Object> getInformationMap() {
        return Collections.unmodifiableMap(this.info);
    }
}
