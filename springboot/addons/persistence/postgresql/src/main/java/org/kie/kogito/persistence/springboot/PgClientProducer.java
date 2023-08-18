package org.kie.kogito.persistence.springboot;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import io.vertx.pgclient.PgPool;

@Component
public class PgClientProducer {

    @Bean
    @ConditionalOnMissingBean
    @Qualifier("kogito")
    public PgPool client(@Value("${kogito.persistence.postgresql.connection.uri}") Optional<String> uri) {
        return uri.isPresent() ? PgPool.pool(uri.get()) : PgPool.pool();
    }
}
