package org.kie.kogito.timer;

public interface Job<T extends JobContext> {

    void execute(T ctx);
}
