package org.kie.kogito.event;

public class KogitoEventStreams {
    public static final String INCOMING = "kogito_incoming_stream";
    public static final String OUTGOING = "kogito_outgoing_stream";
    public static final String MAX_THREADS_PROPERTY = "kogito.quarkus.events.threads.poolSize";
    public static final String QUEUE_SIZE_PROPERTY = "kogito.quarkus.events.threads.queueSize";
    public static final String DEFAULT_MAX_THREADS = "10";
    public static final String DEFAULT_QUEUE_SIZE = "1";
    public static final String THREAD_NAME = "kogito-event-executor";

    private KogitoEventStreams() {
    }
}
