package org.kie.kogito.tracing.decision;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.runtime.Startup;
import io.quarkus.runtime.StartupEvent;

@Singleton
@Startup
public class QuarkusStartupModelPublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuarkusStartupModelPublisher.class);

    @Inject
    QuarkusModelEventEmitter modelEventEmitter;

    // The bean that fires the event at startup must be separated from the kafka connector due to https://github.com/quarkusio/quarkus/issues/12820
    public void publish(@Observes StartupEvent event) {
        LOGGER.debug("Publishing decision models to the kafka topic");
        modelEventEmitter.publishDecisionModels();
    }
}
