package org.kie.kogito.internal.process.event;

import org.kie.api.event.process.ProcessVariableChangedEvent;
import org.kie.kogito.internal.process.runtime.KogitoNodeInstance;

public interface KogitoProcessVariableChangedEvent extends ProcessVariableChangedEvent {

    /**
     * Returns node instance associated with this variable change, if available
     * 
     * @return node instance that changed the variable or null of not available
     */
    KogitoNodeInstance getNodeInstance();
}
