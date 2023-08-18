package org.kie.kogito.codegen.core;

import java.util.Objects;

import org.kie.kogito.codegen.api.ApplicationSection;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;

/**
 * Base implementation for an {@link ApplicationSection}.
 * <p>
 * It provides a skeleton for a "section" in the Application generated class.
 * Subclasses may extend this base class and decorate the provided
 * simple implementations of the interface methods with custom logic.
 */
public abstract class AbstractApplicationSection implements ApplicationSection {

    protected final KogitoBuildContext context;
    private final String sectionClassName;

    public AbstractApplicationSection(KogitoBuildContext context, String sectionClassName) {
        Objects.requireNonNull(context, "context cannot be null");
        this.sectionClassName = sectionClassName;
        this.context = context;
    }

    @Override
    public String sectionClassName() {
        return sectionClassName;
    }
}
