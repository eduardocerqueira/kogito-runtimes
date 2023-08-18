package org.kie.kogito.mongodb;

public class DocumentUnmarshallingException extends RuntimeException {

    private static final long serialVersionUID = -833019897522910159L;

    public DocumentUnmarshallingException(Throwable cause) {
        super(cause);
    }

    public DocumentUnmarshallingException(String processInstanceId, Throwable cause, String msg) {
        super(msg + processInstanceId, cause);
    }
}
