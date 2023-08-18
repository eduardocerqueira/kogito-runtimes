package org.kie.kogito.internal.process.runtime;

import java.util.List;

import org.kie.api.definition.process.Node;
import org.kie.api.definition.process.WorkflowProcess;

public interface KogitoWorkflowProcess extends WorkflowProcess {
    String PUBLIC_VISIBILITY = "Public";
    String PRIVATE_VISIBILITY = "Private";
    String NONE_VISIBILITY = "None";
    String BPMN_TYPE = "BPMN";
    String SW_TYPE = "SW";
    String RULEFLOW_TYPE = "RuleFlow";

    String getVisibility();

    List<Node> getNodesRecursively();
}
