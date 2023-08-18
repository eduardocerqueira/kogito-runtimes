package org.kie.kogito.uow.events;

import org.kie.kogito.uow.UnitOfWork;

public class UnitOfWorkEndEvent extends AbstractUnitOfWorkEvent {

    public UnitOfWorkEndEvent(UnitOfWork unitOfWork) {
        super(unitOfWork);
    }
}
