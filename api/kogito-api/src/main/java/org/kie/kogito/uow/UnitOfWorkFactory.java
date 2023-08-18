package org.kie.kogito.uow;

import org.kie.kogito.event.EventManager;

/**
 * Factory responsible for create instances of UnitOfWork of given
 * implementation type.
 *
 */
public interface UnitOfWorkFactory {

    /**
     * Creates new instance of UnitOfWork implementation backed by this factory.
     * 
     * @param eventManager event manager to publish events
     * @return new unit of work instance
     */
    UnitOfWork create(EventManager eventManager);
}
