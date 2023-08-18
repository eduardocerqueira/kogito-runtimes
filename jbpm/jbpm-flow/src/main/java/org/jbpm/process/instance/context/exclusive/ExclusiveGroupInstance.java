package org.jbpm.process.instance.context.exclusive;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jbpm.process.core.context.exclusive.ExclusiveGroup;
import org.jbpm.process.instance.context.AbstractContextInstance;
import org.kie.api.runtime.process.NodeInstance;
import org.kie.kogito.internal.process.runtime.KogitoNodeInstance;

public class ExclusiveGroupInstance extends AbstractContextInstance {

    private static final long serialVersionUID = 510l;

    private Map<String, KogitoNodeInstance> nodeInstances = new HashMap<>();

    public String getContextType() {
        return ExclusiveGroup.EXCLUSIVE_GROUP;
    }

    public boolean containsNodeInstance(NodeInstance nodeInstance) {
        return nodeInstances.containsKey(((KogitoNodeInstance) nodeInstance).getStringId());
    }

    public void addNodeInstance(KogitoNodeInstance nodeInstance) {
        nodeInstances.put(nodeInstance.getStringId(), nodeInstance);
    }

    public Collection<KogitoNodeInstance> getNodeInstances() {
        return nodeInstances.values();
    }

}
