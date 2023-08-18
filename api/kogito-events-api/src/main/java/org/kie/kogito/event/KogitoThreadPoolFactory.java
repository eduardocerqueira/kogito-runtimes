package org.kie.kogito.event;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class KogitoThreadPoolFactory implements ThreadFactory {
    private final AtomicInteger counter = new AtomicInteger(1);
    private String threadNamePrefix;

    public KogitoThreadPoolFactory(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;
    }

    @Override
    public Thread newThread(Runnable r) {
        String threadName = threadNamePrefix + "-" + counter.getAndIncrement();
        Thread th = new Thread(r);
        th.setName(threadName);
        th.setDaemon(true);
        return th;
    }
}
