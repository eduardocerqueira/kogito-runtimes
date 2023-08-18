package org.kie.kogito.codegen.sample.generator;

public class SampleResource {

    private final String name;
    private final String content;

    public SampleResource(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }
}