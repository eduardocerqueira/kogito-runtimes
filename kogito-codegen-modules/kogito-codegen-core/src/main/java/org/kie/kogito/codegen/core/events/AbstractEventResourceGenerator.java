package org.kie.kogito.codegen.core.events;

import org.kie.kogito.codegen.api.template.TemplatedGenerator;

public abstract class AbstractEventResourceGenerator {

    public static final String TEMPLATE_EVENT_FOLDER = "/class-templates/events/";
    protected TemplatedGenerator generator;

    protected AbstractEventResourceGenerator(TemplatedGenerator generator) {
        this.generator = generator;
    }

    public final String getClassName() {
        return generator.targetTypeName();
    }

    public final String generatedFilePath() {
        return generator.generatedFilePath();
    }

    public abstract String generate();

}
