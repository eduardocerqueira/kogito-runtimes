package org.kie.kogito.mongodb;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.kie.kogito.mongodb.transaction.AbstractTransactionManager;
import org.kie.kogito.testcontainers.KogitoMongoDBContainer;
import org.testcontainers.junit.jupiter.Container;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class TestHelper {

    @Container
    final static KogitoMongoDBContainer mongoDBContainer = new KogitoMongoDBContainer();
    public final static String DB_NAME = "testdb";
    public final static String PROCESS_NAME = "test";
    private static MongoClient mongoClient;

    @BeforeAll
    public static void startContainerAndPublicPortIsAvailable() {
        mongoDBContainer.start();
        mongoClient = MongoClients.create(mongoDBContainer.getReplicaSetUrl());
    }

    @AfterAll
    public static void close() {
        mongoClient.close();
        mongoDBContainer.stop();
    }

    public static MongoClient getMongoClient() {
        return mongoClient;
    }

    public static AbstractTransactionManager getDisabledMongoDBTransactionManager() {
        return new AbstractTransactionManager(mongoClient, false) {
        };
    }

}
