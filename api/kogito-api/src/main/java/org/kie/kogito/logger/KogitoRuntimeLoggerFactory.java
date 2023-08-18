package org.kie.kogito.logger;

import org.kie.api.event.KieRuntimeEventManager;
import org.kie.api.logger.KieLoggers;
import org.kie.api.logger.KieRuntimeLogger;

public class KogitoRuntimeLoggerFactory {

    private KogitoRuntimeLoggerFactory() {

    }

    private static KieLoggers knowledgeRuntimeLoggerFactoryService;

    /**
     * Creates a file logger in the current thread. The file is in XML format, suitable for interpretation by Eclipse's Drools Audit View
     * or other tools. Note that while events are written as they happen, the file will not be flushed until it is closed or the underlying
     * file buffer is filled. If you need real time logging then use a Console Logger or a Threaded File Logger.
     *
     * @param session
     * @param fileName - .log is appended to this.
     * @return
     */
    public static KieRuntimeLogger newFileLogger(KieRuntimeEventManager session, String fileName) {
        return getKnowledgeRuntimeLoggerProvider().newFileLogger(session,
                fileName);
    }

    /**
     * Creates a file logger that executes in a different thread, where information is written on given intervals (in milliseconds).
     * The file is in XML format, suitable for interpretation by Eclipse's Drools Audit View or other tools.
     *
     * @param session
     * @param fileName - .log is appended to this.
     * @param interval - in milliseconds.
     * @return
     */
    public static KieRuntimeLogger newThreadedFileLogger(KieRuntimeEventManager session, String fileName, int interval) {
        return getKnowledgeRuntimeLoggerProvider().newThreadedFileLogger(session, fileName, interval);
    }

    /**
     * Logs events to command line console. This is not in XML format, so it cannot be parsed
     * by other tools, but is in real time and is more human readable.
     *
     * @param session
     * @return
     */
    public static KieRuntimeLogger newConsoleLogger(KieRuntimeEventManager session) {
        return getKnowledgeRuntimeLoggerProvider().newConsoleLogger(session);
    }

    private static synchronized void setKnowledgeRuntimeLoggerProvider(KieLoggers provider) {
        KogitoRuntimeLoggerFactory.knowledgeRuntimeLoggerFactoryService = provider;
    }

    private static synchronized KieLoggers getKnowledgeRuntimeLoggerProvider() {
        if (knowledgeRuntimeLoggerFactoryService == null) {
            loadProvider();
        }
        return knowledgeRuntimeLoggerFactoryService;
    }

    @SuppressWarnings("unchecked")
    private static void loadProvider() {
        try {
            Class<KieLoggers> cls = (Class<KieLoggers>) Class.forName("org.jbpm.audit.KogitoKnowledgeRuntimeLoggerProviderImpl");
            setKnowledgeRuntimeLoggerProvider(cls.newInstance());
        } catch (Exception e) {
            throw new RuntimeException("Provider org.jbpm.audit.KogitoKnowledgeRuntimeLoggerProviderImpl could not be set.", e);
        }
    }
}
