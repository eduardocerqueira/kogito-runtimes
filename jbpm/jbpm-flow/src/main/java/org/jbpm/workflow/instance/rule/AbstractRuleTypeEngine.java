package org.jbpm.workflow.instance.rule;

import java.util.Map;
import java.util.Optional;

import org.jbpm.workflow.core.impl.NodeIoHelper;
import org.jbpm.workflow.instance.node.RuleSetNodeInstance;
import org.kie.api.runtime.KieRuntime;

public interface AbstractRuleTypeEngine {

    default Map<String, Object> getInputs(RuleSetNodeInstance rsni) {
        return NodeIoHelper.processInputs(rsni, rsni::getVariable);
    }

    default KieRuntime getKieRuntime(RuleSetNodeInstance rsni) {
        return Optional.ofNullable(rsni.getRuleSetNode().getKieRuntime()).orElse(() -> rsni.getProcessInstance().getKnowledgeRuntime()).get();
    }
}
