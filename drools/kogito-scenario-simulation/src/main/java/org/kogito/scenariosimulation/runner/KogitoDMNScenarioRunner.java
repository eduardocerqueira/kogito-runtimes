package org.kogito.scenariosimulation.runner;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.drools.scenariosimulation.backend.runner.AbstractRunnerHelper;
import org.drools.scenariosimulation.backend.runner.DMNScenarioRunner;
import org.drools.scenariosimulation.backend.runner.model.ScenarioRunnerDTO;
import org.kie.api.runtime.KieContainer;

public class KogitoDMNScenarioRunner extends DMNScenarioRunner {

    private static final KieContainer mockKieContainer = mockKieContainer();

    public KogitoDMNScenarioRunner(ScenarioRunnerDTO scenarioRunnerDTO) {
        super(mockKieContainer, scenarioRunnerDTO);
    }

    @Override
    protected AbstractRunnerHelper newRunnerHelper() {
        return new KogitoDMNScenarioRunnerHelper();
    }

    /**
     * Temporary hack, it is needed because AbstractScenarioRunner invokes kieContainer.getClassLoader() in the constructor
     * 
     * @return
     */
    private static KieContainer mockKieContainer() {
        InvocationHandler nullHandler = (o, method, objects) -> null;
        return (KieContainer) Proxy.newProxyInstance(KieContainer.class.getClassLoader(),
                new Class[] { KieContainer.class }, nullHandler);
    }
}
