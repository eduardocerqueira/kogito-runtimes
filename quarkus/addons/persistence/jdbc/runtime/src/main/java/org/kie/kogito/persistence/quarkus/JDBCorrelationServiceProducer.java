package org.kie.kogito.persistence.quarkus;

import java.sql.Connection;
import java.sql.SQLException;

import javax.enterprise.inject.Produces;
import javax.sql.DataSource;

import org.kie.kogito.correlation.CorrelationService;
import org.kie.kogito.event.correlation.DefaultCorrelationService;
import org.kie.kogito.persistence.jdbc.DatabaseType;
import org.kie.kogito.persistence.jdbc.correlation.JDBCCorrelationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JDBCorrelationServiceProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCorrelationServiceProducer.class);

    @Produces
    public CorrelationService jdbcCorrelationService(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            if (!DatabaseType.POSTGRES.equals(DatabaseType.getDataBaseType(connection))) {
                return new DefaultCorrelationService();
            }
        } catch (SQLException e) {
            LOGGER.error("Error getting connection for {}", dataSource);
        }
        return new JDBCCorrelationService(dataSource);
    }
}
