package org.kie.kogito.timer.impl;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.kie.kogito.timer.JobHandle;

/**
 * A JobHandle container for scheduling multiple jobs
 */
public class MultiJobHandle extends AbstractJobHandle {

    private static final long serialVersionUID = 510l;

    private long id;

    private AtomicBoolean cancel = new AtomicBoolean(false);

    private final List<JobHandle> jobHandles;

    public MultiJobHandle(long id, List<JobHandle> jobHandles) {
        this.id = id;
        this.jobHandles = jobHandles;
    }

    public long getId() {
        return this.id;
    }

    public Object getJobHandles() {
        return jobHandles;
    }

    public void setCancel(boolean cancel) {
        for (JobHandle handle : jobHandles) {
            handle.setCancel(cancel);
        }
        this.cancel.set(cancel);
    }

    public boolean isCancel() {
        return cancel.get();
    }

}
