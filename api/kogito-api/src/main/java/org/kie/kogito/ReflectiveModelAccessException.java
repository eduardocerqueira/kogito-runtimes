package org.kie.kogito;

public class ReflectiveModelAccessException extends IllegalArgumentException {

    public ReflectiveModelAccessException(Exception e) {
        super(e);
    }

    public ReflectiveModelAccessException(String msg, NoSuchMethodException e) {
        super(msg, e);
    }

    public ReflectiveModelAccessException(String msg) {
        super(msg);
    }
}
