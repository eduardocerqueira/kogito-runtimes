package org.kie.kogito.events.mongodb;

import javax.annotation.PostConstruct;

import org.kie.kogito.mongodb.transaction.AbstractTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mongodb.client.MongoClient;

@Component
public class SpringbootMongoDBEventPublisher extends MongoDBEventPublisher {

    @Autowired
    MongoClient springMongoClient;

    @Autowired
    AbstractTransactionManager springTransactionManager;

    @Value("${kogito.events.processinstances.enabled:true}")
    boolean springEnableProcessInstancesEvents;

    @Value("${kogito.events.usertasks.enabled:true}")
    boolean springEnableUserTasksEvents;

    @Value("${kogito.events.variables.enabled:true}")
    boolean springEnableVariablesEvents;

    @Value("${kogito.events.database:kogito-events}")
    String springEventsDatabaseName;

    @Value("${kogito.events.processinstances.collection:kogitoprocessinstancesevents}")
    String springProcessInstancesEventsCollectionName;

    @Value("${kogito.events.usertasks.collection:kogitousertaskinstancesevents}")
    String springUserTasksEventsCollectionName;

    @Value("${kogito.events.variables.collection:kogitovariablesevents}")
    String springVariablesEventsCollectionName;

    @PostConstruct
    public void setupSpringbootMongoDBEventPublisher() {
        super.configure();
    }

    @Override
    protected MongoClient mongoClient() {
        return this.springMongoClient;
    }

    @Override
    protected AbstractTransactionManager transactionManager() {
        return this.springTransactionManager;
    }

    @Override
    protected boolean processInstancesEvents() {
        return this.springEnableProcessInstancesEvents;
    }

    @Override
    protected boolean userTasksEvents() {
        return this.springEnableUserTasksEvents;
    }

    @Override
    protected boolean variablesEvents() {
        return this.springEnableVariablesEvents;
    }

    @Override
    protected String eventsDatabaseName() {
        return this.springEventsDatabaseName;
    }

    @Override
    protected String processInstancesEventsCollection() {
        return this.springProcessInstancesEventsCollectionName;
    }

    @Override
    protected String userTasksEventsCollection() {
        return this.springUserTasksEventsCollectionName;
    }

    @Override
    protected String variablesEventsCollection() {
        return this.springVariablesEventsCollectionName;
    }
}
