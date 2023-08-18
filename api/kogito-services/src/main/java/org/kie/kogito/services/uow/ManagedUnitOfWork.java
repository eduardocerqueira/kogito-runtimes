package org.kie.kogito.services.uow;

import java.util.function.Consumer;

import org.kie.kogito.uow.UnitOfWork;
import org.kie.kogito.uow.WorkUnit;

/**
 * Unit of work that is managed with injected consumers
 * to react to various life cycle phases
 *
 */
public class ManagedUnitOfWork implements UnitOfWork {

    private UnitOfWork delegate;
    private Consumer<UnitOfWork> onStart;
    private Consumer<UnitOfWork> onEnd;
    private Consumer<UnitOfWork> onAbort;

    public ManagedUnitOfWork(UnitOfWork delegate, Consumer<UnitOfWork> onStart, Consumer<UnitOfWork> onEnd, Consumer<UnitOfWork> onAbort) {
        super();
        this.delegate = delegate;
        this.onStart = onStart;
        this.onEnd = onEnd;
        this.onAbort = onAbort;
    }

    @Override
    public void start() {
        onStart.accept(delegate);
        delegate.start();
    }

    @Override
    public void end() {
        delegate.end();
        onEnd.accept(delegate);
    }

    @Override
    public void abort() {
        delegate.abort();
        onAbort.accept(delegate);
    }

    @Override
    public void intercept(WorkUnit work) {
        delegate.intercept(work);
    }

    public UnitOfWork delegate() {
        return delegate;
    }
}
