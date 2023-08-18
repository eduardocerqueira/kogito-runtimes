package org.jbpm.process.core.event;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.kie.kogito.Application;
import org.kie.kogito.Model;
import org.kie.kogito.event.EventReceiver;
import org.kie.kogito.event.impl.AbstractMessageConsumer;
import org.kie.kogito.event.impl.EventFactoryUtils;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessService;
import org.kie.kogito.process.impl.ProcessServiceImpl;

public class StaticMessageConsumer<M extends Model, D> extends AbstractMessageConsumer<M, D> {

    public static class Builder<M extends Model, D> {
        private final Application application;
        private final Process<M> process;
        private final String trigger;
        private final Class<D> dataClass;
        private EventReceiver receiver;
        ProcessService processService;
        ExecutorService executorService;
        Set<String> correlations = Collections.emptySet();

        protected Builder(Application application, Process<M> process, Class<D> dataClass, String trigger) {
            this.application = application;
            this.process = process;
            this.dataClass = dataClass;
            this.trigger = trigger;
        }

        public Builder<M, D> receiver(EventReceiver receiver) {
            this.receiver = receiver;
            return this;
        }

        public Builder<M, D> service(ProcessService processService) {
            this.processService = processService;
            return this;
        }

        public Builder<M, D> executor(ExecutorService executorService) {
            this.executorService = executorService;
            return this;
        }

        public Builder<M, D> executor(Set<String> correlations) {
            this.correlations = correlations;
            return this;
        }

        public StaticMessageConsumer<M, D> build() {
            StaticMessageConsumer<M, D> consumer = new StaticMessageConsumer<>();
            if (receiver == null) {
                receiver = EventFactoryUtils.getEventReceiver(trigger);
            }
            if (executorService == null) {
                executorService = Executors.newWorkStealingPool();
            }
            if (processService == null) {
                processService = new ProcessServiceImpl(application);
            }
            consumer.init(application, process, trigger, receiver, dataClass, processService, executorService, correlations);
            return consumer;
        }
    }

    public static <M extends Model, D> Builder<M, D> of(Application application, Process<M> process, Class<D> dataClass, String trigger) {
        return new Builder<>(application, process, dataClass, trigger);
    }
}
