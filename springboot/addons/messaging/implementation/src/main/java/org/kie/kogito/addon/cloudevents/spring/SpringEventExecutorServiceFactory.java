package org.kie.kogito.addon.cloudevents.spring;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.kie.kogito.event.EventExecutorServiceFactory;
import org.kie.kogito.event.KogitoEventStreams;
import org.kie.kogito.event.KogitoThreadPoolFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringEventExecutorServiceFactory implements EventExecutorServiceFactory {

    @Value("${" + KogitoEventStreams.MAX_THREADS_PROPERTY + ":#{" + KogitoEventStreams.DEFAULT_MAX_THREADS + "}}")
    int numThreads;

    @Value("${" + KogitoEventStreams.QUEUE_SIZE_PROPERTY + ":#{" + KogitoEventStreams.DEFAULT_QUEUE_SIZE + "}}")
    int queueSize;

    @Override
    public ExecutorService getExecutorService(String channelName) {
        return new ThreadPoolExecutor(1, numThreads, 1L, TimeUnit.MINUTES, new ArrayBlockingQueue<>(queueSize), new KogitoThreadPoolFactory(KogitoEventStreams.THREAD_NAME),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
