package org.jbpm.audit;

import org.drools.core.impl.AbstractRuntime;
import org.drools.kiesession.audit.KnowledgeRuntimeLoggerProviderImpl;
import org.drools.kiesession.audit.WorkingMemoryFileLogger;
import org.kie.api.event.KieRuntimeEventManager;
import org.kie.api.logger.KieRuntimeLogger;

public class KogitoKnowledgeRuntimeLoggerProviderImpl extends KnowledgeRuntimeLoggerProviderImpl {

    @Override
    public KieRuntimeLogger newFileLogger(KieRuntimeEventManager session,
            String fileName,
            int maxEventsInMemory) {
        WorkingMemoryFileLogger logger = new WorkingMemoryFileLogger(session);
        logger.setMaxEventsInMemory(maxEventsInMemory);
        if (fileName != null) {
            logger.setFileName(fileName);
        }
        return registerRuntimeLogger(session, logger);
    }

    private KieRuntimeLogger registerRuntimeLogger(KieRuntimeEventManager session, KieRuntimeLogger logger) {
        if (session instanceof AbstractRuntime) {
            ((AbstractRuntime) session).setLogger(logger);
        }
        return logger;
    }
}
