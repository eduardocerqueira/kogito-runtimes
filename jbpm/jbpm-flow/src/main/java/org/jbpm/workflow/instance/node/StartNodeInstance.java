package org.jbpm.workflow.instance.node;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

import org.jbpm.workflow.core.Node;
import org.jbpm.workflow.core.impl.NodeIoHelper;
import org.jbpm.workflow.core.node.StartNode;
import org.jbpm.workflow.instance.impl.NodeInstanceImpl;
import org.kie.kogito.internal.process.runtime.KogitoNodeInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.jbpm.ruleflow.core.Metadata.TRIGGER_MAPPING_INPUT;

/**
 * Runtime counterpart of a start node.
 * 
 */
public class StartNodeInstance extends NodeInstanceImpl {

    protected static final Logger logger = LoggerFactory.getLogger(StartNodeInstance.class);

    private static final long serialVersionUID = 510l;

    public void internalTrigger(KogitoNodeInstance from, String type) {
        if (type != null) {
            throw new IllegalArgumentException(
                    "A StartNode does not accept incoming connections!");
        }
        if (from != null) {
            throw new IllegalArgumentException(
                    "A StartNode can only be triggered by the process itself!");
        }
        triggerTime = new Date();
        triggerCompleted();
    }

    public void signalEvent(String type, Object event) {
        if (triggerTime == null) {
            triggerTime = new Date();
        }
        String variableName = (String) getStartNode().getMetaData(TRIGGER_MAPPING_INPUT);
        if (variableName != null) {
            Map<String, Object> outputSet = Collections.singletonMap(variableName, event);
            NodeIoHelper.processOutputs(this, key -> outputSet.get(key), varName -> this.getVariable(varName));
        }
        triggerCompleted();
    }

    public StartNode getStartNode() {
        return (StartNode) getNode();
    }

    public void triggerCompleted() {
        ((org.jbpm.workflow.instance.NodeInstanceContainer) getNodeInstanceContainer()).setCurrentLevel(getLevel());
        triggerCompleted(Node.CONNECTION_DEFAULT_TYPE, true);
    }
}
