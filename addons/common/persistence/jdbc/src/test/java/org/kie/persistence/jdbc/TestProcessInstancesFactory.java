package org.kie.persistence.jdbc;

import javax.sql.DataSource;

import org.kie.kogito.persistence.jdbc.AbstractProcessInstancesFactory;
import org.kie.kogito.persistence.jdbc.JDBCProcessInstances;
import org.kie.kogito.process.Process;

import static org.mockito.Mockito.spy;

public class TestProcessInstancesFactory extends AbstractProcessInstancesFactory {

    public TestProcessInstancesFactory(DataSource dataSource, boolean lock) {
        super(dataSource, lock);
    }

    @Override
    public JDBCProcessInstances createProcessInstances(Process<?> process) {
        return spy(super.createProcessInstances(process));
    }

}
