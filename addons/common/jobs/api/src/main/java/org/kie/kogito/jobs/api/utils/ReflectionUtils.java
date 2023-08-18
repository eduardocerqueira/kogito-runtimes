package org.kie.kogito.jobs.api.utils;

import java.util.List;

import org.kie.kogito.jobs.api.JobCallbackPayload;

public class ReflectionUtils {

    private ReflectionUtils() {
    }

    /**
     * Jobs api classes that might be needed for quarkus native compilation.
     */
    public static List<Class<?>> apiReflectiveClasses() {
        return List.of(JobCallbackPayload.class);
    }
}
