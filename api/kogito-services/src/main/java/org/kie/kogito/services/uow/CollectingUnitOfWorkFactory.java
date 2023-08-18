package org.kie.kogito.services.uow;

import org.kie.kogito.event.EventManager;
import org.kie.kogito.uow.UnitOfWork;
import org.kie.kogito.uow.UnitOfWorkFactory;

public class CollectingUnitOfWorkFactory implements UnitOfWorkFactory {

    @Override
    public UnitOfWork create(EventManager eventManager) {
        return new CollectingUnitOfWork(eventManager);
    }

}
