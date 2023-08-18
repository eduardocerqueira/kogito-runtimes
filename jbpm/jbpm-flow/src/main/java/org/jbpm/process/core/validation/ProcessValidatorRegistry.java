package org.jbpm.process.core.validation;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import org.jbpm.ruleflow.core.validation.RuleFlowProcessValidator;
import org.kie.api.definition.process.Process;
import org.kie.api.io.Resource;
import org.kie.kogito.internal.process.runtime.KogitoWorkflowProcess;

public class ProcessValidatorRegistry {

    private static ProcessValidatorRegistry instance;

    private Map<String, ProcessValidator> defaultValidators = new ConcurrentHashMap<>();
    private Set<ProcessValidator> additionalValidators = new CopyOnWriteArraySet<>();

    private ProcessValidatorRegistry() {
        defaultValidators.put(KogitoWorkflowProcess.RULEFLOW_TYPE, RuleFlowProcessValidator.getInstance());
        defaultValidators.put(KogitoWorkflowProcess.BPMN_TYPE, RuleFlowProcessValidator.getInstance());
        defaultValidators.put(KogitoWorkflowProcess.SW_TYPE, RuleFlowProcessValidator.getInstance());
    }

    public static ProcessValidatorRegistry getInstance() {
        if (instance == null) {
            instance = new ProcessValidatorRegistry();
        }

        return instance;
    }

    public void registerAdditonalValidator(ProcessValidator validator) {
        this.additionalValidators.add(validator);
    }

    public ProcessValidator getValidator(Process process, Resource resource) {
        if (!additionalValidators.isEmpty()) {
            for (ProcessValidator validator : additionalValidators) {
                boolean accepted = validator.accept(process, resource);
                if (accepted) {
                    return validator;
                }
            }
        }

        return defaultValidators.get(process.getType());
    }
}
