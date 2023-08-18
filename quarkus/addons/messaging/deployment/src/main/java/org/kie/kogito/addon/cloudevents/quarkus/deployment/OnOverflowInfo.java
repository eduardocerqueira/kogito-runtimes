package org.kie.kogito.addon.cloudevents.quarkus.deployment;

import java.util.Optional;

import org.eclipse.microprofile.reactive.messaging.OnOverflow.Strategy;

public class OnOverflowInfo {

    private final Strategy strategy;

    private final Optional<Long> bufferSize;

    protected OnOverflowInfo(Strategy strategy, Optional<Long> bufferSize) {
        this.strategy = strategy;
        this.bufferSize = bufferSize;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public Optional<Long> getBufferSize() {
        return bufferSize;
    }

    @Override
    public String toString() {
        return "OnOverflowInfo [strategy=" + strategy + ", bufferSize=" + bufferSize + "]";
    }
}
