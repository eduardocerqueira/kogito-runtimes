package org.kie.kogito.serialization.process;

public class ProcessInstanceMarshallerException extends RuntimeException {

    private static final long serialVersionUID = -1676023219884892322L;

    public ProcessInstanceMarshallerException(String msg) {
        super(msg);
    }

    public ProcessInstanceMarshallerException(String msg, Throwable th) {
        super(msg, th);
    }
}
