package org.kie.kogito.persistence.quarkus.rocksdb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.kie.kogito.persistence.rocksdb.RocksDBProcessInstancesFactory;
import org.kie.kogito.process.ProcessInstancesFactory;
import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class RocksDbProcessInstancesFactoryProducer {

    private static final Logger logger = LoggerFactory.getLogger(RocksDbProcessInstancesFactoryProducer.class);
    private Options options;
    private RocksDBProcessInstancesFactory processInstancesFactory;
    @Inject
    RocksDbConfig config;

    @PostConstruct
    void init() throws RocksDBException {
        options = new Options();
        options.setCreateIfMissing(true);
        String dataDir = config.dataDir();
        logger.info("Opening rocksdb in directory {}", dataDir);
        processInstancesFactory = new RocksDBProcessInstancesFactory(options, dataDir);
    }

    @Produces
    ProcessInstancesFactory factory() {
        return processInstancesFactory;
    }

    @PreDestroy
    void cleanup() throws RocksDBException {
        processInstancesFactory.close();
        if (config.destroyDB()) {
            String dataDir = config.dataDir();
            logger.info("Cleaning rocksdb in directory {}", dataDir);
            RocksDB.destroyDB(dataDir, options);
        }
        options.close();
    }
}
