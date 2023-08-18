package org.kie.kogito.incubation.processes.services;

import org.junit.jupiter.api.Test;
import org.kie.kogito.incubation.common.*;
import org.kie.kogito.incubation.processes.LocalProcessId;
import org.kie.kogito.incubation.processes.ProcessInstanceId;
import org.kie.kogito.incubation.processes.TaskId;

import static org.assertj.core.api.Assertions.assertThat;

public class ProcessServiceTypeTest {
    public static class MyDataContext implements DataContext, DefaultCastable {
        int someParam;
    }

    @Test
    public void straightThroughProcesses() {

        // let's just make the compiler happy
        StraightThroughProcessService svc = new StraightThroughProcessService() {
            @Override
            public DataContext evaluate(Id id, DataContext ctx) {
                return ctx;
            }
        };
        MapDataContext ctx = MapDataContext.create(); // suppose there is a Map-like structure
                                                      // (it could be even just Map)
        LocalProcessId someProcessId = new LocalProcessId("some.process");

        // set a context using a Map-like interface
        ctx.set("someParam", 1);

        // evaluate the process
        DataContext result =
                svc.evaluate(someProcessId, ctx);

        // bind the data in the result to a typed bean
        MyDataContext mdc = result.as(MyDataContext.class);

        MapLikeDataContext map = mdc.as(MapLikeDataContext.class);

        assertThat(mdc.someParam).isOne(); // get the typed value from the POJO
        assertThat(map.get("someParam")).isEqualTo(1); // get the object value from map-like

        assertThat(someProcessId.toLocalId().asLocalUri().path()).isEqualTo("/processes/some.process");

    }

    @Test
    public void statefulProcesses() {
        StatefulProcessService svc = new StatefulProcessService() {

            @Override
            public ExtendedDataContext signal(LocalId processId, DataContext dataContext) {
                return ExtendedDataContext.ofData(EmptyDataContext.Instance);
            }

            @Override
            public ExtendedDataContext create(LocalId processId, DataContext dataContext) {
                return ExtendedDataContext.ofData(EmptyDataContext.Instance);
            }

            @Override
            public ExtendedDataContext update(LocalId processId, DataContext dataContext) {
                return ExtendedDataContext.ofData(EmptyDataContext.Instance);
            }

            @Override
            public ExtendedDataContext abort(LocalId processId) {
                return ExtendedDataContext.ofData(EmptyDataContext.Instance);
            }

            @Override
            public ExtendedDataContext get(LocalId processId) {
                return ExtendedDataContext.ofData(EmptyDataContext.Instance);
            }
        };

        MapDataContext ctx = MapDataContext.create(); // suppose there is a Map-like structure
        // (it could be even just Map)
        LocalProcessId someProcessId = new LocalProcessId("some.process");

        // set a context using a Map-like interface
        ctx.set("someParam", 1);

        // evaluate the process
        DataContext result =
                svc.create(someProcessId, ctx);

        ProcessInstanceId processInstanceId = someProcessId.instances().get("some.instance.id");

        assertThat(processInstanceId.toLocalId().asLocalUri().path()).isEqualTo("/processes/some.process/instances/some.instance.id");

        TaskId taskId = processInstanceId.tasks().get("some.task.id");

        assertThat(taskId.toLocalId().asLocalUri().path()).isEqualTo("/processes/some.process/instances/some.instance.id/tasks/some.task.id");

    }
}
