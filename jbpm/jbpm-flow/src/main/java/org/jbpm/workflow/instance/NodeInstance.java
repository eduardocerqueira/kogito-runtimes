package org.jbpm.workflow.instance;

import java.util.Date;
import java.util.Map;

import org.jbpm.process.instance.ContextInstance;
import org.kie.api.definition.process.Node;
import org.kie.kogito.internal.process.runtime.KogitoNodeInstance;
import org.kie.kogito.internal.process.runtime.KogitoProcessInstance;

/**
 * Represents a node instance in a RuleFlow. This is the runtime counterpart
 * of a node, containing all runtime state. Node instance classes also
 * contain the logic on what to do when it is being triggered (start
 * executing) or completed (end of execution).
 * 
 */
public interface NodeInstance extends KogitoNodeInstance {

    void trigger(KogitoNodeInstance from, String type);

    void cancel();

    Node getNode();

    ContextInstance resolveContextInstance(String contextId, Object param);

    int getLevel();

    void setDynamicParameters(Map<String, Object> dynamicParameters);

    int getSlaCompliance();

    Date getSlaDueDate();

    String getSlaTimerId();

    default KogitoProcessInstance getKogitoProcessInstance() {
        return (KogitoProcessInstance) getProcessInstance();
    }

}
