package org.kie.persistence.postgresql;

import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
class PostgresqlProcessInstancesWithLockIT extends PostgresqlProcessInstancesIT {

    @Override
    boolean lock() {
        return true;
    }
}
