package org.kie.kogito.addons.springboot.k8s;

import org.kie.kogito.addons.k8s.CacheNames;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
@EnableCaching
public class CachingConfig {

    public static final String CACHE_MANAGER = "caffeineCacheManager";

    @Bean
    public Caffeine<Object, Object> caffeineConfig() {
        return Caffeine.newBuilder();
    }

    @Primary //marking as primary to not clash with Infinispan Persistence. Could be removed in the future. See: https://issues.redhat.com/browse/KOGITO-6111
    @Bean(CACHE_MANAGER)
    public CaffeineCacheManager cacheManager(Caffeine<Object, Object> caffeine) {
        final CaffeineCacheManager cacheManager = new CaffeineCacheManager(CacheNames.CACHE_BY_NAME, CacheNames.CACHE_BY_LABELS);
        cacheManager.setCaffeine(caffeine);
        return cacheManager;
    }

}
