package org.kie.kogito.persistence.postgresql;

import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstancesFactory;

import io.vertx.pgclient.PgPool;

public abstract class AbstractProcessInstancesFactory implements ProcessInstancesFactory {

    private final Long queryTimeout;
    private final PgPool client;
    private final Boolean lock;

    // Constructor for DI
    protected AbstractProcessInstancesFactory() {
        this(null, 10000L, false);
    }

    public AbstractProcessInstancesFactory(PgPool client, Long queryTimeout, Boolean lock) {
        this.client = client;
        this.queryTimeout = queryTimeout;
        this.lock = lock;
    }

    public PgPool client() {
        return this.client;
    }

    public boolean lock() {
        return lock;
    }

    @Override
    public PostgresqlProcessInstances createProcessInstances(Process<?> process) {
        return new PostgresqlProcessInstances(process, client(), queryTimeout, lock());
    }
}
