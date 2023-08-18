package org.kie.kogito.process.impl;

import org.kie.kogito.process.Signal;

public final class Sig<T> implements Signal<T> {

    private final String channel;
    private final T payload;
    private String referenceId;

    public static <T> org.kie.kogito.process.Signal<T> of(String channel, T payload) {
        return new Sig<>(channel, payload);
    }

    public static <T> org.kie.kogito.process.Signal<T> of(String channel, T payload, String referenceId) {
        return new Sig<>(channel, payload, referenceId);
    }

    protected Sig(String channel, T payload) {
        this.channel = channel;
        this.payload = payload;
    }

    protected Sig(String channel, T payload, String referenceId) {
        this.channel = channel;
        this.payload = payload;
        this.referenceId = referenceId;
    }

    @Override
    public String channel() {
        return channel;
    }

    @Override
    public T payload() {
        return payload;
    }

    @Override
    public String referenceId() {
        return referenceId;
    }
}
