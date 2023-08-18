package org.kie.kogito.serverless.workflow.parser.schema;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.microprofile.openapi.models.media.Schema;

class RefSchemas {

    private RefSchemas() {
    }

    private static class ThreadInfo {
        private final String id;
        private final Map<String, Schema> map = new HashMap<>();
        private int counter;
        private String baseURI;

        private ThreadInfo(String id) {
            this.id = id;
        }
    }

    private static ThreadLocal<ThreadInfo> threadInfo = new ThreadLocal<>();

    public static void init(String id) {
        threadInfo.set(new ThreadInfo(id));
    }

    public static Map<String, Schema> get() {
        return threadInfo.get().map;
    }

    public static void baseURI(String baseURI) {
        if (baseURI != null) {
            int lastIndexOf = baseURI.lastIndexOf('/');
            threadInfo.get().baseURI = lastIndexOf != -1 ? baseURI.substring(0, lastIndexOf) : baseURI;
        }
    }

    public static String getBaseURI() {
        return threadInfo.get().baseURI;
    }

    public static String getKey() {
        ThreadInfo t = threadInfo.get();
        return t.id + "_nested_" + ++t.counter;
    }

    public static void reset() {
        threadInfo.remove();
    }
}
