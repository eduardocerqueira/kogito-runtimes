package org.kie.kogito.core.rules.incubation.quarkus.support;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;
import org.drools.ruleunits.api.DataStream;

@ApplicationScoped
public class DataSourceProvider {
    @Produces
    <T> DataStore<T> makeDataStore() {
        return DataSource.createStore();
    }

    @Produces
    <T> DataStream<T> makeDataStream() {
        return DataSource.createStream();
    }
}
