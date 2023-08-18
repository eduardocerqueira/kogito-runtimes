package org.kie.kogito.persistence.quarkus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.kie.kogito.mongodb.AbstractProcessInstancesFactory;
import org.kie.kogito.mongodb.transaction.AbstractTransactionManager;

import com.mongodb.client.MongoClient;

@ApplicationScoped
public class MongoDBProcessInstancesFactory extends AbstractProcessInstancesFactory {

    public MongoDBProcessInstancesFactory() {
        super(null, null, null, null);
    }

    @Inject
    public MongoDBProcessInstancesFactory(MongoClient mongoClient,
            AbstractTransactionManager transactionManager,
            @ConfigProperty(name = "quarkus.mongodb.database", defaultValue = "kogito") String dbName,
            @ConfigProperty(name = "kogito.persistence.optimistic.lock", defaultValue = "false") Boolean lock) {
        super(mongoClient, dbName, lock, transactionManager);
    }

}
