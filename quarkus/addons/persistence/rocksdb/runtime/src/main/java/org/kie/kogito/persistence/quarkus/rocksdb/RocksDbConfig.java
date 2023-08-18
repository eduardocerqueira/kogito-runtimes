package org.kie.kogito.persistence.quarkus.rocksdb;

import io.quarkus.runtime.annotations.StaticInitSafe;
import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;
import io.smallrye.config.WithName;

@ConfigMapping(prefix = "kogito.persistence.rocksdb")
@StaticInitSafe
public interface RocksDbConfig {

    @WithName("data.dir")
    @WithDefault("rockdstemp")
    String dataDir();

    @WithName("clean")
    @WithDefault("false")
    boolean destroyDB();
}
