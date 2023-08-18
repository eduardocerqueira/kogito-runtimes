package org.jbpm.process.instance;

import java.util.HashMap;
import java.util.Map;

import org.jbpm.ruleflow.core.RuleFlowProcess;
import org.jbpm.ruleflow.instance.RuleFlowProcessInstanceFactory;
import org.kie.api.definition.process.Process;

public class ProcessInstanceFactoryRegistry {

    public static final ProcessInstanceFactoryRegistry INSTANCE =
            new ProcessInstanceFactoryRegistry();

    private Map<Class<? extends Process>, ProcessInstanceFactory> registry;

    private ProcessInstanceFactoryRegistry() {
        this.registry = new HashMap<>();

        // hard wired nodes:
        register(RuleFlowProcess.class,
                new RuleFlowProcessInstanceFactory());
    }

    public void register(Class<? extends Process> cls,
            ProcessInstanceFactory factory) {
        this.registry.put(cls,
                factory);
    }

    public ProcessInstanceFactory getProcessInstanceFactory(Process process) {
        return this.registry.get(process.getClass());
    }
}
