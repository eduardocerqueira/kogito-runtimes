package org.kie.kogito.persistence.springboot;

import org.kie.kogito.persistence.postgresql.AbstractProcessInstancesFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.vertx.pgclient.PgPool;

@Component
public class PostgresqlProcessInstancesFactory extends AbstractProcessInstancesFactory {

    @Autowired
    public PostgresqlProcessInstancesFactory(@Qualifier("kogito") PgPool client,
            @Value("${kogito.persistence.query.timeout.millis:10000}") Long queryTimeout,
            @Value("${kogito.persistence.optimistic.lock:false}") Boolean lock) {
        super(client, queryTimeout, lock);
    }

    protected PostgresqlProcessInstancesFactory() {
        super();
    }

}
