package org.kie.kogito.infinispan;

import org.infinispan.client.hotrod.RemoteCacheManager;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstancesFactory;

public abstract class AbstractProcessInstancesFactory implements ProcessInstancesFactory {

    private final RemoteCacheManager cacheManager;
    private final Boolean lock;
    private final String template;

    public AbstractProcessInstancesFactory(RemoteCacheManager cacheManager, Boolean lock, String template) {
        this.cacheManager = cacheManager;
        this.lock = lock;
        this.template = template;
    }

    @Override
    public CacheProcessInstances createProcessInstances(Process<?> process) {
        return new CacheProcessInstances(process, cacheManager, template, lock);
    }

}
