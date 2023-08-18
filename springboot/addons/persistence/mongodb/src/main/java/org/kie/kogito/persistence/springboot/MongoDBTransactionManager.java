package org.kie.kogito.persistence.springboot;

import org.kie.kogito.mongodb.transaction.AbstractTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mongodb.client.MongoClient;

@Component
public class MongoDBTransactionManager extends AbstractTransactionManager {

    @Autowired
    public MongoDBTransactionManager(MongoClient mongoClient,
            @Value("${kogito.persistence.transaction.enabled:false}") Boolean enabled) {
        super(mongoClient, enabled);
    }
}
