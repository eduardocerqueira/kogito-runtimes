package org.kie.kogito.codegen.api.utils;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.codegen.api.context.impl.JavaKogitoBuildContext;
import org.kie.kogito.codegen.api.context.impl.QuarkusKogitoBuildContext;
import org.kie.kogito.codegen.api.context.impl.SpringBootKogitoBuildContext;

import static java.util.Collections.emptyList;
import static java.util.Collections.singleton;

public class KogitoContextTestUtils {

    private KogitoContextTestUtils() {
        // utility class
    }

    public static Stream<Arguments> contextBuilders() {
        return Stream.of(
                Arguments.of(JavaKogitoBuildContext.builder()),
                Arguments.of(QuarkusKogitoBuildContext.builder()),
                Arguments.of(SpringBootKogitoBuildContext.builder()));
    }

    public static Predicate<String> mockClassAvailabilityResolver(Collection<String> includedClasses, Collection<String> excludedClasses) {
        return mockClassAvailabilityResolver(includedClasses, excludedClasses, KogitoContextTestUtils.class.getClassLoader());
    }

    public static Predicate<String> mockClassAvailabilityResolver(Collection<String> includedClasses, Collection<String> excludedClasses, ClassLoader classLoader) {
        return className -> {
            if (includedClasses.contains(className)) {
                return true;
            } else if (excludedClasses.contains(className)) {
                return false;
            }
            try {
                classLoader.loadClass(className);
                return true;
            } catch (ClassNotFoundException ex) {
                return false;
            }
        };
    }

    public static KogitoBuildContext.Builder withLegacyApi(KogitoBuildContext.Builder contextBuilder) {
        return contextBuilder
                .withClassAvailabilityResolver(mockClassAvailabilityResolver(singleton("org.kie.api.runtime.KieRuntimeBuilder"), emptyList()));
    }

}
