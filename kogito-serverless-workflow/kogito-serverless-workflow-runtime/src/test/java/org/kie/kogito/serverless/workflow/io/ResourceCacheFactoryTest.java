package org.kie.kogito.serverless.workflow.io;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ResourceCacheFactoryTest {

    private URI uri;

    private AtomicInteger counter;

    @BeforeEach
    void setup() {
        uri = URI.create("http:://www.google.com");
        counter = new AtomicInteger();
    }

    @Test
    void testDefaultResourceCache() {
        final int numOfTimes = 3;
        callIt(numOfTimes);
        assertThat(counter.get()).isEqualTo(1);
    }

    @Test
    void testCustomResourceCache() {
        ResourceCache defaultCache = ResourceCacheFactory.getCache();
        try {
            ResourceCacheFactory.setResourceCache(new TestResourceCache());
            callIt(7);
            assertThat(counter.get()).isEqualTo(1);

        } finally {
            ResourceCacheFactory.setResourceCache(defaultCache);
        }
    }

    private static class TestResourceCache implements ResourceCache {
        private Map<URI, byte[]> map = new ConcurrentHashMap<>();

        @Override
        public byte[] get(URI uri, Function<URI, byte[]> retrieveCall) {
            return map.computeIfAbsent(uri, retrieveCall);
        }
    }

    private void callIt(int numOfTimes) {
        ResourceCache cache = ResourceCacheFactory.getCache();
        while (numOfTimes-- > 0) {
            cache.get(uri, this::called);
        }
    }

    private byte[] called(URI uri) {
        return new byte[] { (byte) counter.incrementAndGet() };
    }
}
