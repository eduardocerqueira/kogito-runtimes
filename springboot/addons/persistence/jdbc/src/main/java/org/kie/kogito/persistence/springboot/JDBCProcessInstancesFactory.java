package org.kie.kogito.persistence.springboot;

import javax.sql.DataSource;

import org.kie.kogito.persistence.jdbc.AbstractProcessInstancesFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JDBCProcessInstancesFactory extends AbstractProcessInstancesFactory {

    @Autowired
    public JDBCProcessInstancesFactory(DataSource dataSource,
            @Value("${kogito.persistence.optimistic.lock:false}") Boolean lock) {
        super(dataSource, lock);
    }

}
