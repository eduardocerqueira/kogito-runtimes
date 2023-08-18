package org.kie.kogito.persistence.rocksdb;

import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstancesFactory;
import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

public class RocksDBProcessInstancesFactory implements ProcessInstancesFactory, AutoCloseable {

    private final RocksDB db;

    public RocksDBProcessInstancesFactory(Options options, String dbLocation) throws RocksDBException {
        this.db = RocksDB.open(options, dbLocation);
    }

    @Override
    public RocksDBProcessInstances<?> createProcessInstances(Process<?> process) {
        return new RocksDBProcessInstances<>(process, db);
    }

    @Override
    public void close() {
        db.close();
    }
}
