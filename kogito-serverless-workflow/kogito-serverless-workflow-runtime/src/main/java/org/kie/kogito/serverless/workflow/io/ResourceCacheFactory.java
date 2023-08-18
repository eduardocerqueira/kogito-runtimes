package org.kie.kogito.serverless.workflow.io;

import java.net.URI;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

public class ResourceCacheFactory {
    private static final AtomicReference<ResourceCache> cache = new AtomicReference<>(new LocalResourceCache());

    public static ResourceCache getCache() {
        return cache.get();
    }

    private static class LocalResourceCache implements ResourceCache {
        private final Map<URI, byte[]> map = Collections.synchronizedMap(new WeakHashMap<>());

        @Override
        public byte[] get(URI uri, Function<URI, byte[]> retrieveCall) {
            return map.computeIfAbsent(uri, retrieveCall);
        }

    }

    protected static void setResourceCache(ResourceCache newCache) {
        cache.set(newCache);
    }

    private ResourceCacheFactory() {
    }
}
