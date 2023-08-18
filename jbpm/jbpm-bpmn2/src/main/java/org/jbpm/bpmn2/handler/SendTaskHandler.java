package org.jbpm.bpmn2.handler;

import org.kie.kogito.internal.process.runtime.KogitoWorkItem;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemHandler;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendTaskHandler implements KogitoWorkItemHandler {

    private static final Logger logger = LoggerFactory.getLogger(SendTaskHandler.class);

    public void executeWorkItem(KogitoWorkItem workItem, KogitoWorkItemManager manager) {
        String message = (String) workItem.getParameter("Message");
        logger.debug("Sending message: {}", message);
        manager.completeWorkItem(workItem.getStringId(), null);
    }

    public void abortWorkItem(KogitoWorkItem workItem, KogitoWorkItemManager manager) {
        // Do nothing, cannot be aborted
    }
}
