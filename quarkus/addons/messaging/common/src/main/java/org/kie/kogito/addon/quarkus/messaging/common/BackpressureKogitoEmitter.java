package org.kie.kogito.addon.quarkus.messaging.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;

import io.vertx.core.impl.ConcurrentHashSet;

@ApplicationScoped
public class BackpressureKogitoEmitter implements QuarkusEmitterController {

    private Set<String> statuses = new ConcurrentHashSet<>();
    private Map<String, Runnable> handlers = new HashMap<>();

    @Override
    public boolean resume(String channelName) {
        boolean result = statuses.remove(channelName);
        if (result) {
            Runnable handler = handlers.get(channelName);
            if (handler != null) {
                handler.run();
            }
        }
        return result;
    }

    @Override
    public boolean stop(String channelName) {
        return statuses.add(channelName);
    }

    @Override
    public boolean isEnabled(String channelName) {
        return !statuses.contains(channelName);
    }

    public void registerHandler(String channelName, Runnable runnable) {
        handlers.put(channelName, runnable);
    }
}
