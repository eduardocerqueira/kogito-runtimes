package org.kie.kogito.timer;

/**
 * An interface for Job Handles
 */
public interface JobHandle {

    public long getId();

    public void setCancel(boolean cancel);

    public boolean isCancel();
}
