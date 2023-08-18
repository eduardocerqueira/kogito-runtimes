package org.kie.kogito.persistence.quarkus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.kie.kogito.persistence.jdbc.AbstractProcessInstancesFactory;

@ApplicationScoped
public class JDBCProcessInstancesFactory extends AbstractProcessInstancesFactory {

    @Inject
    public JDBCProcessInstancesFactory(DataSource dataSource,
            @ConfigProperty(name = "kogito.persistence.optimistic.lock", defaultValue = "false") Boolean lock) {
        super(dataSource, lock);
    }

    public JDBCProcessInstancesFactory() {
    }

}
