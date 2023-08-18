package org.kie.kogito.codegen.sample.core;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SampleModelImpl implements SampleModel {

    private final String name;
    private final String content;
    private final int numberOfCopy;

    public SampleModelImpl(String name, String content, int numberOfCopy) {
        this.name = name;
        this.content = content;
        this.numberOfCopy = numberOfCopy;
    }

    @Override
    public String execute() {
        return IntStream.range(0, numberOfCopy)
                .mapToObj(i -> content)
                .collect(Collectors.joining("-"));
    }

    @Override
    public String name() {
        return name;
    }

}