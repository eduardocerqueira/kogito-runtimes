package org.kie.kogito.addon.quarkus.messaging.common;

import java.util.concurrent.ExecutorService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.kie.kogito.event.EventExecutorServiceFactory;
import org.kie.kogito.event.KogitoEventStreams;

import io.quarkus.arc.DefaultBean;

@ApplicationScoped
@DefaultBean
public class QuarkusEventExecutorServiceFactory implements EventExecutorServiceFactory {

    @ConfigProperty(name = KogitoEventStreams.MAX_THREADS_PROPERTY, defaultValue = KogitoEventStreams.DEFAULT_MAX_THREADS)
    int numThreads;

    @ConfigProperty(name = KogitoEventStreams.QUEUE_SIZE_PROPERTY, defaultValue = KogitoEventStreams.DEFAULT_QUEUE_SIZE)
    int queueSize;

    @Inject
    QuarkusEmitterController emitterStatus;

    @Override
    public ExecutorService getExecutorService(String channelName) {
        return new QuarkusEventThreadPool(numThreads, queueSize, emitterStatus, channelName);
    }
}
