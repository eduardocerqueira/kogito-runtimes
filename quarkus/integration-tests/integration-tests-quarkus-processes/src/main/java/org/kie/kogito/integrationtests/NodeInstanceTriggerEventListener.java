package org.kie.kogito.integrationtests;

import javax.enterprise.context.ApplicationScoped;

import org.kie.api.event.process.ProcessNodeLeftEvent;
import org.kie.kogito.internal.process.event.DefaultKogitoProcessEventListener;
import org.kie.kogito.internal.process.runtime.KogitoNodeInstance;

import static java.lang.String.format;

@ApplicationScoped
public class NodeInstanceTriggerEventListener extends DefaultKogitoProcessEventListener {

    @Override
    public void afterNodeLeft(ProcessNodeLeftEvent event) {
        KogitoNodeInstance ni = (KogitoNodeInstance) event.getNodeInstance();
        if (ni.getTriggerTime() == null) {
            throw new IllegalStateException(format("Node instance for node %s, contains a null trigger time", ni.getNodeName()));
        }
    }
}
