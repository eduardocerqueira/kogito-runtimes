package org.kie.kogito.tracing.decision;

import javax.inject.Singleton;

import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.kie.kogito.tracing.EventEmitter;
import org.reactivestreams.Publisher;

import io.smallrye.mutiny.operators.multi.processors.BroadcastProcessor;

@Singleton
public class QuarkusTraceEventEmitter implements EventEmitter {

    private final BroadcastProcessor<String> eventSubject;

    public QuarkusTraceEventEmitter() {
        this.eventSubject = BroadcastProcessor.create();
    }

    @Outgoing("kogito-tracing-decision")
    public Publisher<String> getEventPublisher() {
        return eventSubject.toHotStream();
    }

    @Override
    public void emit(final String payload) {
        eventSubject.onNext(payload);
    }
}
