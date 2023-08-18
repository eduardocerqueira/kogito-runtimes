package org.kie.persistence.jdbc;

public class PostgreSqlProcessInstancesLockIT extends PostgreSqlProcessInstancesIT {

    @Override
    boolean lock() {
        return true;
    }
}
