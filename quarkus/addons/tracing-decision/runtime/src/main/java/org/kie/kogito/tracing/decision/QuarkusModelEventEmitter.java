package org.kie.kogito.tracing.decision;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.kie.kogito.decision.DecisionModelResourcesProvider;
import org.reactivestreams.Publisher;

import io.smallrye.mutiny.operators.multi.processors.BroadcastProcessor;

@Singleton
public class QuarkusModelEventEmitter extends BaseModelEventEmitter {

    private final BroadcastProcessor<String> eventSubject;

    @Inject
    public QuarkusModelEventEmitter(final DecisionModelResourcesProvider decisionModelResourcesProvider) {
        super(decisionModelResourcesProvider);
        this.eventSubject = BroadcastProcessor.create();
    }

    @Override
    public void publishDecisionModels() {
        super.publishDecisionModels();
    }

    @Outgoing("kogito-tracing-model")
    public Publisher<String> getEventPublisher() {
        return eventSubject.toHotStream();
    }

    @Override
    public void emit(final String payload) {
        eventSubject.onNext(payload);
    }
}
