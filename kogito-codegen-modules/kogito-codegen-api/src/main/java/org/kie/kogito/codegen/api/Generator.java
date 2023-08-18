package org.kie.kogito.codegen.api;

import java.util.Collection;
import java.util.Optional;

import org.drools.codegen.common.GeneratedFile;
import org.drools.codegen.common.GeneratedFileType;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;

/**
 * A code generator for a part of the platform, e.g. rules, processes, etc.
 */
public interface Generator {

    GeneratedFileType REST_TYPE = GeneratedFileType.of("REST", GeneratedFileType.Category.SOURCE, true, true);
    GeneratedFileType MODEL_TYPE = GeneratedFileType.of("MODEL", GeneratedFileType.Category.SOURCE, true, true);

    String CONFIG_PREFIX = "kogito.codegen.";

    /**
     * Returns the "section" of the Application class corresponding to rules.
     * e.g the processes() method with processes().createMyProcess() etc.
     *
     */
    Optional<ApplicationSection> section();

    /**
     * Returns the collection of all the files that have been generated/compiled
     *
     */
    Collection<GeneratedFile> generate();

    Optional<ConfigGenerator> configGenerator();

    KogitoBuildContext context();

    String name();

    boolean isEmpty();

    /**
     * Override this method to specify an order of execution
     * 
     * @return
     */
    default int priority() {
        return Integer.MAX_VALUE;
    }

    default boolean isEnabled() {
        return !isEmpty() && context().getApplicationProperty(CONFIG_PREFIX + name())
                .map("true"::equalsIgnoreCase)
                .orElse(true);
    }
}
