package org.kie.persistence.jdbc;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.kie.kogito.testcontainers.KogitoOracleSqlContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import oracle.jdbc.pool.OracleDataSource;

@Testcontainers
public class OracleProcessInstancesIT extends AbstractProcessInstancesIT {

    @Container
    private final static KogitoOracleSqlContainer ORACLE_CONTAINER = new KogitoOracleSqlContainer();
    private static final String ORACLE_TIMEZONE_PROPERTY = "oracle.jdbc.timezoneAsRegion";
    private static OracleDataSource ORACLE_DATA_SOURCE;

    @BeforeAll
    public static void start() {
        try {
            ORACLE_DATA_SOURCE = new OracleDataSource();
            ORACLE_DATA_SOURCE.setURL(ORACLE_CONTAINER.getJdbcUrl());
            ORACLE_DATA_SOURCE.setUser(ORACLE_CONTAINER.getUsername());
            ORACLE_DATA_SOURCE.setPassword(ORACLE_CONTAINER.getPassword());
            System.setProperty(ORACLE_TIMEZONE_PROPERTY, "false");
            initMigration(ORACLE_CONTAINER, "oracle");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create oracle datasource");
        }
    }

    @AfterAll
    public static void stop() {
        System.clearProperty(ORACLE_TIMEZONE_PROPERTY);
    }

    protected DataSource getDataSource() {
        return ORACLE_DATA_SOURCE;
    }
}
