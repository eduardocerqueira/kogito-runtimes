/**
 * 
 */
package org.jbpm.workflow.instance.node;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.workflow.core.Node;
import org.jbpm.workflow.instance.impl.NodeInstanceImpl;
import org.kie.api.runtime.process.NodeInstance;
import org.kie.kogito.internal.process.runtime.KogitoNodeInstance;

public class MockNodeInstance extends NodeInstanceImpl {

    private static final long serialVersionUID = 510l;

    private Map<String, List<NodeInstance>> triggers = new HashMap<String, List<NodeInstance>>();
    private MockNode mockNode;

    public MockNodeInstance(MockNode mockNode) {
        this.mockNode = mockNode;
    }

    public org.kie.api.definition.process.Node getNode() {
        return mockNode;
    }

    public MockNode getMockNode() {
        return mockNode;
    }

    public void internalTrigger(KogitoNodeInstance from, String type) {
        if (type == null) {
            throw new IllegalArgumentException(
                    "Trigger type is null!");
        }
        triggerTime = new Date();
        List<NodeInstance> list = triggers.get(type);
        if (list == null) {
            list = new ArrayList<NodeInstance>();
            triggers.put(type, list);
        }
        list.add(from);
    }

    public Map<String, List<NodeInstance>> getTriggers() {
        return triggers;
    }

    public int hashCode() {
        return (int) getNodeId();
    }

    public boolean equals(Object object) {
        if (object == null || (!(object instanceof MockNodeInstance))) {
            return false;
        }
        MockNodeInstance other = (MockNodeInstance) object;
        return getNodeId() == other.getNodeId();
    }

    public void triggerCompleted() {
        triggerCompleted(Node.CONNECTION_DEFAULT_TYPE, true);
    }
}
