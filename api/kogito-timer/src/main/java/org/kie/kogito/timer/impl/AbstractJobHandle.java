package org.kie.kogito.timer.impl;

import org.kie.kogito.timer.JobHandle;

public abstract class AbstractJobHandle implements JobHandle {

    private JobHandle previous;
    private JobHandle next;

    public JobHandle getPrevious() {
        return previous;
    }

    public void setPrevious(JobHandle previous) {
        this.previous = previous;
    }

    public void nullPrevNext() {
        previous = null;
        next = null;
    }

    public void setNext(JobHandle next) {
        this.next = next;
    }

    public JobHandle getNext() {
        return next;
    }
}
