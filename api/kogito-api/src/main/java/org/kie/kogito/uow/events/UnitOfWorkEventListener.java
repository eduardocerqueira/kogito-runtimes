package org.kie.kogito.uow.events;

public interface UnitOfWorkEventListener {

    default void onBeforeStartEvent(UnitOfWorkStartEvent event) {
        //NO-OP
    }

    default void onAfterEndEvent(UnitOfWorkEndEvent event) {
        //NO-OP
    }

    default void onAfterAbortEvent(UnitOfWorkAbortEvent event) {
        //NO-OP
    }
}
