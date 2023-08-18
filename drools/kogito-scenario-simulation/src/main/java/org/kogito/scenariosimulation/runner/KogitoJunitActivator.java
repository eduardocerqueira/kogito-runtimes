package org.kogito.scenariosimulation.runner;

import org.drools.scenariosimulation.backend.runner.ScenarioJunitActivator;
import org.drools.scenariosimulation.backend.runner.model.ScenarioRunnerDTO;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

public class KogitoJunitActivator extends ScenarioJunitActivator {

    public KogitoJunitActivator(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    @Override
    protected void runChild(ScenarioRunnerDTO child, RunNotifier notifier) {
        KogitoDMNScenarioRunner scenarioRunner = new KogitoDMNScenarioRunner(child);
        scenarioRunner.run(notifier);
    }
}