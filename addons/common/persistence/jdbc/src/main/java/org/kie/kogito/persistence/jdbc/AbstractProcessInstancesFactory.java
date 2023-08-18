package org.kie.kogito.persistence.jdbc;

import javax.sql.DataSource;

import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstancesFactory;

public abstract class AbstractProcessInstancesFactory implements ProcessInstancesFactory {

    private final DataSource dataSource;
    private final Boolean lock;

    protected AbstractProcessInstancesFactory() {
        this(null, false);
    }

    public AbstractProcessInstancesFactory(DataSource dataSource, Boolean lock) {
        this.dataSource = dataSource;
        this.lock = lock;
    }

    @Override
    public JDBCProcessInstances createProcessInstances(Process<?> process) {
        return new JDBCProcessInstances(process, dataSource, lock);
    }
}
