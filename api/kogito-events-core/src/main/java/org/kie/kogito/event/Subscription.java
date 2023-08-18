package org.kie.kogito.event;

import java.util.concurrent.CompletionStage;
import java.util.function.Function;

public class Subscription<T, S> {
    private final Function<T, CompletionStage<?>> consumer;
    private final Converter<S, T> converter;

    public Subscription(Function<T, CompletionStage<?>> consumer, Converter<S, T> converter) {
        this.consumer = consumer;
        this.converter = converter;
    }

    public Function<T, CompletionStage<?>> getConsumer() {
        return consumer;
    }

    public Converter<S, T> getConverter() {
        return converter;
    }
}
