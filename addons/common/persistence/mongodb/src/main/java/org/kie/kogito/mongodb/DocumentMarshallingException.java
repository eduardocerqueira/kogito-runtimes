package org.kie.kogito.mongodb;

public class DocumentMarshallingException extends RuntimeException {

    private static final long serialVersionUID = -707257541887233373L;

    public DocumentMarshallingException(Throwable cause) {
        super(cause);
    }

    public DocumentMarshallingException(String processInstanceId, Throwable cause, String msg) {
        super(msg + processInstanceId, cause);
    }
}
