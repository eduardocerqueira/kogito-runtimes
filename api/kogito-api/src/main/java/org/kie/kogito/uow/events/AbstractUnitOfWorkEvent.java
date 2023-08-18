package org.kie.kogito.uow.events;

import org.kie.kogito.uow.UnitOfWork;

public abstract class AbstractUnitOfWorkEvent implements UnitOfWorkEvent {

    private UnitOfWork unitOfWork;

    public AbstractUnitOfWorkEvent(UnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    @Override
    public UnitOfWork getUnitOfWork() {
        return unitOfWork;
    }
}
