package org.kie.kogito.internal.process.runtime;

import java.util.Collection;
import java.util.function.Predicate;

import org.kie.api.runtime.process.NodeInstanceContainer;

public interface KogitoNodeInstanceContainer extends NodeInstanceContainer {

    /**
     * Returns all node instances that are currently active
     * within this container.
     *
     * @return the list of node instances currently active
     */
    default Collection<KogitoNodeInstance> getKogitoNodeInstances() {
        return (Collection<KogitoNodeInstance>) (Object) getNodeInstances();
    }

    /**
     * Returns the node instance with the given id, or <code>null</code>
     * if the node instance cannot be found.
     *
     * @param nodeInstanceId
     * @return the node instance with the given id
     */
    KogitoNodeInstance getNodeInstance(String nodeInstanceId);

    /**
     * Return nodes that matches a filter
     * 
     * @param filter condition to be fulfilled by node
     * @param recursive if should process child nodes
     * @return nodes fullfilling the filter
     */
    Collection<KogitoNodeInstance> getKogitoNodeInstances(Predicate<KogitoNodeInstance> filter, boolean recursive);
}
