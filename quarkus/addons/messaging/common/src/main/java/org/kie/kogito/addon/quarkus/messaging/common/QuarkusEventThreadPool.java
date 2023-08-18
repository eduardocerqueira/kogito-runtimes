package org.kie.kogito.addon.quarkus.messaging.common;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.kie.kogito.event.KogitoEventStreams;
import org.kie.kogito.event.KogitoThreadPoolFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuarkusEventThreadPool extends ThreadPoolExecutor {

    private static final Logger logger = LoggerFactory.getLogger(QuarkusEventThreadPool.class);

    private final Deque<Runnable> overflowBuffer = new LinkedList<>();
    private final QuarkusEmitterController kogitoEmitter;
    private final String channelName;

    public QuarkusEventThreadPool(int numThreads, int queueSize, QuarkusEmitterController kogitoEmitter, String channelName) {
        super(1, numThreads, 1L, TimeUnit.MINUTES, new ArrayBlockingQueue<>(queueSize));
        setThreadFactory(new KogitoThreadPoolFactory(KogitoEventStreams.THREAD_NAME));
        setRejectedExecutionHandler(new NonBlockingRejectedExecutionHandler());
        this.kogitoEmitter = kogitoEmitter;
        this.channelName = channelName;
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        Runnable queued;
        boolean resume;
        synchronized (overflowBuffer) {
            queued = overflowBuffer.pollFirst();
            resume = queued != null && overflowBuffer.isEmpty();
        }
        if (queued != null) {
            logger.trace("Addding runnable {} back to the executor", queued);
            super.execute(queued);
            if (resume) {
                logger.trace("Resuming emission");
                kogitoEmitter.resume(channelName);
            }
        }
    }

    private class NonBlockingRejectedExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            if (!executor.isShutdown()) {
                logger.trace("Rejecting runnable {}. Stopping emission", r);
                kogitoEmitter.stop(channelName);
                synchronized (overflowBuffer) {
                    overflowBuffer.addLast(r);
                }
            }
        }
    }
}
