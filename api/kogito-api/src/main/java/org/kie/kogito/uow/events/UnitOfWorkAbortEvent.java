package org.kie.kogito.uow.events;

import org.kie.kogito.uow.UnitOfWork;

public class UnitOfWorkAbortEvent extends AbstractUnitOfWorkEvent {

    public UnitOfWorkAbortEvent(UnitOfWork unitOfWork) {
        super(unitOfWork);
    }
}
