package org.kie.kogito.events.mongodb;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.kie.kogito.mongodb.transaction.AbstractTransactionManager;

import com.mongodb.client.MongoClient;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@QuarkusTest
class QuarkusMongoDBEventPublisherTest {

    @Inject
    QuarkusMongoDBEventPublisher publisher;

    @InjectSpy
    MongoClient mongoClient;

    @Inject
    AbstractTransactionManager transactionManager;

    @Test
    void setup() {
        publisher.setupQuarkusMongoDBEventPublisher();
        verify(mongoClient, atLeastOnce()).getDatabase(eq("testDB"));
    }

    @Test
    void mongoClient() {
        assertEquals(mongoClient.getDatabase("testDB"), publisher.mongoClient().getDatabase("testDB"));
    }

    @Test
    void transactionManager() {
        assertEquals(transactionManager, publisher.transactionManager());
    }

    @Test
    void processInstancesEvents() {
        assertFalse(publisher.processInstancesEvents());
    }

    @Test
    void userTasksEvents() {
        assertFalse(publisher.userTasksEvents());
    }

    @Test
    void variablesEvents() {
        assertFalse(publisher.variablesEvents());
    }

    @Test
    void eventsDatabaseName() {
        assertEquals("testDB", publisher.eventsDatabaseName());
    }

    @Test
    void processInstancesEventsCollection() {
        assertEquals("testPICollection", publisher.processInstancesEventsCollection());
    }

    @Test
    void userTasksEventsCollection() {
        assertEquals("testUTCollection", publisher.userTasksEventsCollection());
    }

    @Test
    void variablesEventsCollection() {
        assertEquals("testVCollection", publisher.variablesEventsCollection());
    }
}
