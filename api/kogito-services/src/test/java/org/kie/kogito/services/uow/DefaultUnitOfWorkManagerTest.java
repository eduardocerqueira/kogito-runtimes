package org.kie.kogito.services.uow;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.kogito.uow.UnitOfWork;
import org.kie.kogito.uow.UnitOfWorkManager;
import org.kie.kogito.uow.WorkUnit;
import org.kie.kogito.uow.events.UnitOfWorkEventListener;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class DefaultUnitOfWorkManagerTest {

    private UnitOfWorkManager unitOfWorkManager;
    private UnitOfWorkEventListener listener = mock(UnitOfWorkEventListener.class);

    @BeforeEach
    public void setup() {
        this.unitOfWorkManager = new DefaultUnitOfWorkManager(new CollectingUnitOfWorkFactory());
        this.unitOfWorkManager.register(listener);
    }

    @Test
    public void testFallbackUnitOfWork() {

        UnitOfWork unit = unitOfWorkManager.currentUnitOfWork();
        assertThat(unit).isNotNull().isInstanceOf(PassThroughUnitOfWork.class);
    }

    @Test
    public void testUnitOfWorkStartEnd() {

        UnitOfWork unit = unitOfWorkManager.newUnitOfWork();
        assertThat(unit).isNotNull().isInstanceOf(ManagedUnitOfWork.class);
        assertThat(((ManagedUnitOfWork) unit).delegate()).isInstanceOf(CollectingUnitOfWork.class);

        final AtomicInteger counter = new AtomicInteger(0);
        assertThat(counter).hasValue(0);

        BaseWorkUnit dummyWork = new BaseWorkUnit(counter, (d) -> ((AtomicInteger) d).incrementAndGet());
        unit.start();
        unit.intercept(dummyWork);
        unit.end();

        assertThat(counter).hasValue(1);
        verify(listener).onBeforeStartEvent(any());
        verify(listener).onAfterEndEvent(any());
        verify(listener, never()).onAfterAbortEvent(any());
    }

    @Test
    public void testUnitOfWorkStartAbort() {

        UnitOfWork unit = unitOfWorkManager.newUnitOfWork();
        assertThat(unit).isNotNull().isInstanceOf(ManagedUnitOfWork.class);

        final AtomicInteger counter = new AtomicInteger(0);
        assertThat(counter).hasValue(0);

        BaseWorkUnit dummyWork = new BaseWorkUnit(counter, (d) -> ((AtomicInteger) d).incrementAndGet());
        unit.start();
        unit.intercept(dummyWork);
        unit.abort();

        assertThat(counter).hasValue(0);
        verify(listener).onBeforeStartEvent(any());
        verify(listener, never()).onAfterEndEvent(any());
        verify(listener).onAfterAbortEvent(any());
    }

    @Test
    public void testUnitOfWorkStartOnFinishedUnit() {

        UnitOfWork unit = unitOfWorkManager.newUnitOfWork();
        assertThat(unit).isNotNull().isInstanceOf(ManagedUnitOfWork.class);

        unit.start();
        unit.abort();
        assertThrows(IllegalStateException.class, () -> unit.start(), "Cannot start already completed unit");
        assertThrows(IllegalStateException.class, () -> unit.end(), "Cannot end already completed unit");
        assertThrows(IllegalStateException.class, () -> unit.abort(), "Cannot abort already completed unit");
        assertThrows(IllegalStateException.class, () -> unit.intercept(null), "Cannot intercept on already completed unit");
    }

    @Test
    public void testUnitOfWorkAddWorkOnNotStarted() {

        UnitOfWork unit = unitOfWorkManager.newUnitOfWork();
        assertThat(unit).isNotNull().isInstanceOf(ManagedUnitOfWork.class);

        final AtomicInteger counter = new AtomicInteger(0);
        assertThat(counter).hasValue(0);

        WorkUnit<AtomicInteger> dummyWork = WorkUnit.create(counter, (d) -> d.incrementAndGet());

        assertThrows(IllegalStateException.class, () -> unit.intercept(dummyWork), "Cannot intercept on not started unit");
        verify(listener, never()).onBeforeStartEvent(any());
        verify(listener, never()).onAfterEndEvent(any());
        verify(listener, never()).onAfterAbortEvent(any());
    }

    @Test
    public void testUnitOfWorkStartEndOrdered() {

        UnitOfWork unit = unitOfWorkManager.newUnitOfWork();
        assertThat(unit).isNotNull().isInstanceOf(ManagedUnitOfWork.class);
        assertThat(((ManagedUnitOfWork) unit).delegate()).isInstanceOf(CollectingUnitOfWork.class);

        final AtomicInteger counter = new AtomicInteger(0);
        assertThat(counter).hasValue(0);

        final AtomicInteger picounter = new AtomicInteger(0);

        BaseWorkUnit dummyWork = new BaseWorkUnit(counter, (d) -> ((AtomicInteger) d).incrementAndGet());
        ProcessInstanceWorkUnit<?> piWork = new ProcessInstanceWorkUnit<>(null, (d) -> picounter.set(counter.get()));
        unit.start();
        // make sure that dummyWork is first added and then piWork
        unit.intercept(dummyWork);
        unit.intercept(piWork);
        unit.end();

        // after execution the pi should be 0 as this is the initial value of counter which will indicate
        // it was invoked before dummyWork that increments it
        assertThat(counter).hasValue(1);
        assertThat(picounter).hasValue(0);
    }
}
