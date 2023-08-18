package org.jbpm.workflow.instance.node;

import java.util.Date;

import org.jbpm.workflow.core.Node;
import org.jbpm.workflow.instance.impl.NodeInstanceImpl;
import org.kie.kogito.internal.process.runtime.KogitoNodeInstance;

public class ThrowLinkNodeInstance extends NodeInstanceImpl {

    private static final long serialVersionUID = 20110505L;

    @Override
    public void internalTrigger(KogitoNodeInstance from, String type) {
        triggerTime = new Date();
        this.triggerCompleted();
    }

    public void triggerCompleted() {
        this.triggerCompleted(
                Node.CONNECTION_DEFAULT_TYPE, true);
    }

}
