package org.kie.kogito.process;

public enum ProcessInstanceReadMode {

    READ_ONLY(true),
    MUTABLE(false);

    private boolean isReadOnly;

    public boolean isReadOnly() {
        return isReadOnly;
    }

    ProcessInstanceReadMode(boolean isReadOnly) {
        this.isReadOnly = isReadOnly;
    }
}
