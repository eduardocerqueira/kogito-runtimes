package org.kie.kogito.persistence.quarkus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.kie.kogito.persistence.postgresql.AbstractProcessInstancesFactory;

import io.vertx.pgclient.PgPool;

@ApplicationScoped
public class PostgresqlProcessInstancesFactory extends AbstractProcessInstancesFactory {

    @Inject
    public PostgresqlProcessInstancesFactory(PgPool client,
            @ConfigProperty(name = "kogito.persistence.query.timeout.millis", defaultValue = "10000") Long queryTimeout,
            @ConfigProperty(name = "kogito.persistence.optimistic.lock", defaultValue = "false") Boolean lock) {
        super(client, queryTimeout, lock);
    }

    protected PostgresqlProcessInstancesFactory() {
        super();
    }

}
