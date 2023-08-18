package org.jbpm.test.util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.kie.kogito.internal.process.event.DefaultKogitoProcessEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultCountDownProcessEventListener extends DefaultKogitoProcessEventListener {

    private static final Logger logger = LoggerFactory.getLogger(DefaultCountDownProcessEventListener.class);

    protected CountDownLatch latch;

    public DefaultCountDownProcessEventListener() {

    }

    public DefaultCountDownProcessEventListener(int threads) {
        this.latch = new CountDownLatch(threads);
    }

    public boolean waitTillCompleted() {
        return waitTillCompleted(10000);
    }

    public boolean waitTillCompleted(long timeOut) {
        try {
            return latch.await(timeOut, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.debug("Interrputed thread while waiting for all triggers");
            return false;
        }
    }

    public void reset(int threads) {
        this.latch = new CountDownLatch(threads);
    }

    protected void countDown() {
        latch.countDown();
    }
}
