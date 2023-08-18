package org.kie.kogito.addon.quarkus.messaging.common;

public interface QuarkusEmitterController {

    boolean resume(String channelName);

    boolean stop(String channelName);

    boolean isEnabled(String channelName);
}
