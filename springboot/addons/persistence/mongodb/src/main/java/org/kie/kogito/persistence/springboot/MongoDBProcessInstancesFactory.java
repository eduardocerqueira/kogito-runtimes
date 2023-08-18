package org.kie.kogito.persistence.springboot;

import org.kie.kogito.mongodb.AbstractProcessInstancesFactory;
import org.kie.kogito.mongodb.transaction.AbstractTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mongodb.client.MongoClient;

@Component
public class MongoDBProcessInstancesFactory extends AbstractProcessInstancesFactory {

    @Autowired
    public MongoDBProcessInstancesFactory(MongoClient mongoClient,
            AbstractTransactionManager transactionManager,
            @Value("${kogito.persistence.optimistic.lock:false}") Boolean lock,
            @Value("${spring.data.mongodb.database:kogito}") String dbName) {
        super(mongoClient, dbName, lock, transactionManager);
    }

}
