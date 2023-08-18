package org.kie.kogito.event;

import java.util.concurrent.ExecutorService;

public interface EventExecutorServiceFactory {

    public ExecutorService getExecutorService(String channelName);
}
