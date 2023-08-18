package org.kie.kogito.events.mongodb;

import javax.enterprise.context.ApplicationScoped;

import org.kie.kogito.mongodb.transaction.AbstractTransactionManager;

import com.mongodb.client.ClientSession;

import io.quarkus.test.Mock;

@Mock
@ApplicationScoped
public class MockTransactionManager extends AbstractTransactionManager {

    public MockTransactionManager() {
        super(null, false);
    }

    @Override
    public ClientSession getClientSession() {
        return null;
    }

}
