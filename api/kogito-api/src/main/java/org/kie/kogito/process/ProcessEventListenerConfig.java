package org.kie.kogito.process;

import java.util.List;

import org.kie.api.event.process.ProcessEventListener;

public interface ProcessEventListenerConfig {

    List<ProcessEventListener> listeners();

}
