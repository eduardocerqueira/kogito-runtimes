package org.kie.kogito.persistence.springboot;

import org.infinispan.client.hotrod.RemoteCacheManager;
import org.kie.kogito.infinispan.AbstractProcessInstancesFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class InfinispanProcessInstancesFactory extends AbstractProcessInstancesFactory {

    @Autowired
    public InfinispanProcessInstancesFactory(RemoteCacheManager cacheManager,
            @Value("${kogito.persistence.optimistic.lock:false}") Boolean lock,
            @Value("${kogito.persistence.infinispan.template:#{null}}") String templateName) {
        super(cacheManager, lock, templateName);
    }

}
