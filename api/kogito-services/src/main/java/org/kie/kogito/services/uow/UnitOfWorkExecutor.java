package org.kie.kogito.services.uow;

import java.util.function.Supplier;

import org.kie.kogito.process.ProcessInstanceExecutionException;
import org.kie.kogito.uow.UnitOfWork;
import org.kie.kogito.uow.UnitOfWorkManager;

public class UnitOfWorkExecutor {

    private UnitOfWorkExecutor() {

    }

    public static <T> T executeInUnitOfWork(UnitOfWorkManager uowManager, Supplier<T> supplier) {
        T result = null;
        UnitOfWork uow = uowManager.newUnitOfWork();

        try {
            uow.start();

            result = supplier.get();
            uow.end();

            return result;
        } catch (ProcessInstanceExecutionException e) {
            uow.end();

            throw e;
        } catch (Exception e) {
            uow.abort();
            if (e instanceof RuntimeException) {
                throw (RuntimeException) e;
            } else {
                throw new RuntimeException(e);
            }
        }

    }
}
