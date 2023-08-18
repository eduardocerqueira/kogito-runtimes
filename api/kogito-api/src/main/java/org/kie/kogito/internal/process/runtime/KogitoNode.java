package org.kie.kogito.internal.process.runtime;

import org.kie.api.definition.process.Node;
import org.kie.api.definition.process.NodeContainer;

public interface KogitoNode extends Node {

    NodeContainer getParentContainer();
}
