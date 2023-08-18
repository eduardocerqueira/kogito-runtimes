package org.kie.kogito.internal.process.runtime;

import java.util.Date;

import org.kie.api.runtime.process.NodeInstance;

public interface KogitoNodeInstance extends NodeInstance {

    /**
     * The id of the node instance. This is unique within the
     * node instance container this node instance lives in.
     *
     * @return the id of the node instance
     */
    String getStringId();

    /**
     * The id of the node definition this node instance refers to. The node
     * represents the definition that this node instance was based
     * on.
     *
     * @return the definition id of the node this node instance refers to
     */
    String getNodeDefinitionId();

    /**
     * Returns the time when this node instance was triggered
     * 
     * @return actual trigger time
     */
    Date getTriggerTime();

    /**
     * Returns the time when this node instance was left, might be null if node instance is still active
     * 
     * @return actual leave time
     */
    Date getLeaveTime();
}
