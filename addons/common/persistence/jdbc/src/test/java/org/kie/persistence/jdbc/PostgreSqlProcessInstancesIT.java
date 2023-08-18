package org.kie.persistence.jdbc;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeAll;
import org.kie.kogito.testcontainers.KogitoPostgreSqlContainer;
import org.postgresql.ds.PGSimpleDataSource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class PostgreSqlProcessInstancesIT extends AbstractProcessInstancesIT {

    @Container
    private final static KogitoPostgreSqlContainer PG_CONTAINER = new KogitoPostgreSqlContainer();
    private static PGSimpleDataSource PG_DATA_SOURCE;

    @BeforeAll
    public static void start() {
        PG_DATA_SOURCE = new PGSimpleDataSource();
        PG_DATA_SOURCE.setUrl(PG_CONTAINER.getJdbcUrl());
        PG_DATA_SOURCE.setUser(PG_CONTAINER.getUsername());
        PG_DATA_SOURCE.setPassword(PG_CONTAINER.getPassword());
        initMigration(PG_CONTAINER, "postgresql");
    }

    protected DataSource getDataSource() {
        return PG_DATA_SOURCE;
    }
}
