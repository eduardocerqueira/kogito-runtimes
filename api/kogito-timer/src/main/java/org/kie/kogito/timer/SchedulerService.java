package org.kie.kogito.timer;

public interface SchedulerService<T extends JobHandle> {

    /**
     * Schedule a job for later execution
     *
     * @param job
     * @param ctx
     * @param trigger
     *
     * @return
     */
    T scheduleJob(Job job, JobContext ctx, Trigger trigger);

    /**
     * Remove the job identified by the given job handle from the
     * scheduled queue
     *
     * @param jobHandle the job identity handle
     *
     * @return
     */
    boolean removeJob(T jobHandle);
}
