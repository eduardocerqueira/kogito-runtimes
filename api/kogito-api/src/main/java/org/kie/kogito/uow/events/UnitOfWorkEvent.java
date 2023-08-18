package org.kie.kogito.uow.events;

import org.kie.kogito.uow.UnitOfWork;

public interface UnitOfWorkEvent {

    UnitOfWork getUnitOfWork();

}
