package org.kie.kogito.persistence.quarkus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.kie.kogito.mongodb.transaction.AbstractTransactionManager;

import com.mongodb.client.MongoClient;

@ApplicationScoped
public class MongoDBTransactionManager extends AbstractTransactionManager {

    public MongoDBTransactionManager() {
        super(null, false);
    }

    @Inject
    public MongoDBTransactionManager(MongoClient mongoClient,
            @ConfigProperty(name = "kogito.persistence.transaction.enabled", defaultValue = "false") Boolean enabled) {
        super(mongoClient, enabled);
    }
}
