package org.kie.kogito.services.uow;

import java.util.function.Consumer;

import org.kie.kogito.uow.WorkUnit;

public class BaseWorkUnit<T> implements WorkUnit<T> {

    private T data;
    private Consumer<T> action;
    private Consumer<T> compensation;
    private Integer priority;

    public BaseWorkUnit(T data, Consumer<T> action) {
        this(data, action, null);
    }

    public BaseWorkUnit(T data, Consumer<T> action, Consumer<T> compensation) {
        this(data, action, compensation, DEFAULT_PRIORITY);
    }

    public BaseWorkUnit(T data, Consumer<T> action, Consumer<T> compensation, Integer priority) {
        this.data = data;
        this.action = action;
        this.compensation = compensation;
        this.priority = priority;
    }

    @Override
    public T data() {
        return data;
    }

    @Override
    public Integer priority() {
        return priority;
    }

    @Override
    public void perform() {
        action.accept(data());
    }

    @Override
    public void abort() {
        if (compensation != null) {
            compensation.accept(data());
        }
    }
}
