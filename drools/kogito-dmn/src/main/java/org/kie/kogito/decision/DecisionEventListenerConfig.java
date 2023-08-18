package org.kie.kogito.decision;

import java.util.List;

import org.kie.dmn.api.core.event.DMNRuntimeEventListener;

public interface DecisionEventListenerConfig {

    List<DMNRuntimeEventListener> listeners();

}
