package org.jbpm.test.util;

import org.kie.api.event.process.ProcessCompletedEvent;

public class ProcessCompletedCountDownProcessEventListener extends DefaultCountDownProcessEventListener {

    public ProcessCompletedCountDownProcessEventListener() {
        super(1);
    }

    public ProcessCompletedCountDownProcessEventListener(int threads) {
        super(threads);
    }

    @Override
    public void afterProcessCompleted(ProcessCompletedEvent event) {
        countDown();
    }

}