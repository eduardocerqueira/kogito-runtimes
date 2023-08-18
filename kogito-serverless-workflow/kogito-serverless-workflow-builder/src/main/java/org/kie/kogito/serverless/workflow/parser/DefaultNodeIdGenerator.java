package org.kie.kogito.serverless.workflow.parser;

import java.util.concurrent.atomic.AtomicLong;

public class DefaultNodeIdGenerator implements NodeIdGenerator {

    private DefaultNodeIdGenerator() {
    }

    private AtomicLong idCounter = new AtomicLong(1);

    public static DefaultNodeIdGenerator get() {
        return new DefaultNodeIdGenerator();
    }

    @Override
    public long getId() {
        return idCounter.getAndIncrement();
    }

}
