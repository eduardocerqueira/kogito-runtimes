package org.kie.kogito.services.uow;

import java.util.LinkedHashSet;
import java.util.Set;

import org.kie.kogito.event.EventManager;
import org.kie.kogito.event.impl.BaseEventManager;
import org.kie.kogito.uow.UnitOfWork;
import org.kie.kogito.uow.UnitOfWorkFactory;
import org.kie.kogito.uow.UnitOfWorkManager;
import org.kie.kogito.uow.events.UnitOfWorkAbortEvent;
import org.kie.kogito.uow.events.UnitOfWorkEndEvent;
import org.kie.kogito.uow.events.UnitOfWorkEventListener;
import org.kie.kogito.uow.events.UnitOfWorkStartEvent;

/**
 * Default implementation of the UnitOfWorkManager that is backed by
 * thread local to keep the associated unit of work.
 *
 */
public class DefaultUnitOfWorkManager implements UnitOfWorkManager {
    // uses thread local to associate unit of works to execution context/thread
    private ThreadLocal<UnitOfWork> currentUnitOfWork = new ThreadLocal<>();
    // uses pass through unit of work as fallback if no unit of work has been started
    private UnitOfWork fallbackUnitOfWork = new PassThroughUnitOfWork();
    // factory used to create unit of work 
    private UnitOfWorkFactory factory;

    private EventManager eventManager = new BaseEventManager();

    private Set<UnitOfWorkEventListener> listeners = new LinkedHashSet<>();

    public DefaultUnitOfWorkManager(UnitOfWorkFactory factory) {
        super();
        this.factory = factory;
    }

    public DefaultUnitOfWorkManager(UnitOfWork fallbackUnitOfWork, UnitOfWorkFactory factory) {
        super();
        this.fallbackUnitOfWork = fallbackUnitOfWork;
        this.factory = factory;
    }

    @Override
    public UnitOfWork currentUnitOfWork() {
        UnitOfWork unit = currentUnitOfWork.get();

        if (unit == null) {
            return fallbackUnitOfWork;
        }
        return unit;
    }

    @Override
    public UnitOfWork newUnitOfWork() {
        return new ManagedUnitOfWork(factory.create(eventManager), this::onStart, this::onEnd, this::onAbort);
    }

    protected void onStart(UnitOfWork unit) {
        this.associate(unit);
        listeners.forEach(l -> l.onBeforeStartEvent(new UnitOfWorkStartEvent(unit)));
    }

    protected void onEnd(UnitOfWork unit) {
        this.dissociate(unit);
        listeners.forEach(l -> l.onAfterEndEvent(new UnitOfWorkEndEvent(unit)));
    }

    protected void onAbort(UnitOfWork unit) {
        this.dissociate(unit);
        listeners.forEach(l -> l.onAfterAbortEvent(new UnitOfWorkAbortEvent(unit)));
    }

    protected void associate(UnitOfWork unit) {
        currentUnitOfWork.set(unit);
    }

    protected void dissociate(UnitOfWork unit) {
        currentUnitOfWork.set(null);
    }

    @Override
    public EventManager eventManager() {
        return eventManager;
    }

    @Override
    public void register(UnitOfWorkEventListener listener) {
        listeners.add(listener);
    }
}
