package org.kie.kogito.persistence.jdbc;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

abstract class Repository {

    static final String INSERT = "INSERT INTO process_instances (id, payload, process_id, process_version, version) VALUES (?, ?, ?, ?, ?)";
    static final String FIND_ALL = "SELECT payload, version FROM process_instances WHERE process_id = ?";
    static final String FIND_BY_ID = "SELECT payload, version FROM process_instances WHERE process_id = ? and id = ?";
    static final String UPDATE = "UPDATE process_instances SET payload = ? WHERE process_id = ? and id = ?";
    static final String UPDATE_WITH_LOCK = "UPDATE process_instances SET payload = ?, version = ? WHERE process_id = ? and id = ? and version = ?";
    static final String DELETE = "DELETE FROM process_instances WHERE process_id = ? and id = ?";
    static final String PROCESS_VERSION_EQUALS_TO = "and process_version = ?";
    static final String PROCESS_VERSION_IS_NULL = "and process_version is null";

    static class Record {
        private final byte[] payload;
        private final long version;

        public byte[] getPayload() {
            return payload;
        }

        public long getVersion() {
            return version;
        }

        public Record(byte[] payload, long version) {
            this.payload = payload;
            this.version = version;
        }
    }

    abstract void insertInternal(String processId, String processVersion, UUID id, byte[] payload);

    abstract void updateInternal(String processId, String processVersion, UUID id, byte[] payload);

    abstract boolean updateWithLock(String processId, String processVersion, UUID id, byte[] payload, long version);

    abstract boolean deleteInternal(String processId, String processVersion, UUID id);

    abstract Optional<Record> findByIdInternal(String processId, String processVersion, UUID id);

    abstract Stream<Record> findAllInternal(String processId, String processVersion);

    protected RuntimeException uncheckedException(Exception ex, String message, Object... param) {
        return new RuntimeException(String.format(message, param), ex);
    }
}
