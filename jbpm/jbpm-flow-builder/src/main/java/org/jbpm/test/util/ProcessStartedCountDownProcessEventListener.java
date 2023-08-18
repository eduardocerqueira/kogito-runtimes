package org.jbpm.test.util;

import org.kie.api.event.process.ProcessStartedEvent;

public class ProcessStartedCountDownProcessEventListener extends DefaultCountDownProcessEventListener {

    public ProcessStartedCountDownProcessEventListener() {
        super(1);
    }

    public ProcessStartedCountDownProcessEventListener(int threads) {
        super(threads);
    }

    @Override
    public void afterProcessStarted(ProcessStartedEvent event) {
        countDown();
    }

}