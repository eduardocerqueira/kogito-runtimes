package org.kie.kogito.serverless.workflow.io;

import java.net.URI;
import java.util.function.Function;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import io.quarkus.cache.Cache;
import io.quarkus.cache.CacheName;
import io.quarkus.runtime.Startup;

@Startup
public class QuarkusResourceCache {

    @Inject
    @CacheName("SWFResourceCache")
    Cache cache;

    @PostConstruct
    void init() {
        ResourceCacheFactory.setResourceCache(this::get);
    }

    private byte[] get(URI uri, Function<URI, byte[]> retrieveCall) {
        return cache.get(uri, retrieveCall).await().indefinitely();
    }
}
