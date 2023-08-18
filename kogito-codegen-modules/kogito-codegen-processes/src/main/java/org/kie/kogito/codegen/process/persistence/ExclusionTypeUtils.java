package org.kie.kogito.codegen.process.persistence;

import java.util.function.Predicate;

public final class ExclusionTypeUtils {

    private ExclusionTypeUtils() {
        // nothing to do
    }

    public static Predicate<String> createTypeExclusions() {
        return type -> "com.fasterxml.jackson.databind.JsonNode".equals(type);
    }

}
