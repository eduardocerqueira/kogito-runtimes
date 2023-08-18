package org.kie.kogito.persistence.quarkus;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.kie.kogito.infinispan.AbstractProcessInstancesFactory;

@ApplicationScoped
public class InfinispanProcessInstancesFactory extends AbstractProcessInstancesFactory {

    public InfinispanProcessInstancesFactory() {
        super(null, null, null);
    }

    @Inject
    public InfinispanProcessInstancesFactory(RemoteCacheManager cacheManager,
            @ConfigProperty(name = "kogito.persistence.optimistic.lock", defaultValue = "false") Boolean lock,
            @ConfigProperty(name = "kogito.persistence.infinispan.template") Optional<String> templateName) {
        super(cacheManager, lock, templateName.orElse(null));
    }

}
