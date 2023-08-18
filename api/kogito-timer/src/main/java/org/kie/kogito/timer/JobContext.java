package org.kie.kogito.timer;

import java.io.Serializable;

public interface JobContext extends Serializable {
    /**
     * This method should only be called by the scheduler
     */
    void setJobHandle(JobHandle jobHandle);

    JobHandle getJobHandle();
}
