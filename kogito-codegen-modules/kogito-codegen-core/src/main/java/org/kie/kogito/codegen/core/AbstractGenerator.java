package org.kie.kogito.codegen.core;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

import org.drools.codegen.common.GeneratedFile;
import org.kie.kogito.codegen.api.ConfigGenerator;
import org.kie.kogito.codegen.api.Generator;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;

public abstract class AbstractGenerator implements Generator {

    private final ConfigGenerator configGenerator;
    private final KogitoBuildContext context;
    private final String name;

    protected AbstractGenerator(KogitoBuildContext context, String name) {
        this(context, name, null);
    }

    protected AbstractGenerator(KogitoBuildContext context, String name, ConfigGenerator configGenerator) {
        Objects.requireNonNull(context, "context cannot be null");
        this.name = name;
        this.context = context;
        this.configGenerator = configGenerator;
    }

    @Override
    public KogitoBuildContext context() {
        return this.context;
    }

    @Override
    public String name() {
        return name;
    }

    protected String applicationCanonicalName() {
        return context.getPackageName() + ".Application";
    }

    @Override
    public Optional<ConfigGenerator> configGenerator() {
        return Optional.ofNullable(configGenerator);
    }

    @Override
    public final Collection<GeneratedFile> generate() {
        if (isEmpty()) {
            return Collections.emptySet();
        }
        return internalGenerate();
    }

    protected abstract Collection<GeneratedFile> internalGenerate();
}
