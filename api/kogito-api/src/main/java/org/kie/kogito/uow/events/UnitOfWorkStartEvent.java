package org.kie.kogito.uow.events;

import org.kie.kogito.uow.UnitOfWork;

public class UnitOfWorkStartEvent extends AbstractUnitOfWorkEvent {

    public UnitOfWorkStartEvent(UnitOfWork unitOfWork) {
        super(unitOfWork);
    }
}
