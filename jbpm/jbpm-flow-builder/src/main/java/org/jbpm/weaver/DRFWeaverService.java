package org.jbpm.weaver;

import org.kie.api.io.ResourceType;

public class DRFWeaverService extends AbstractWeaverService {

    @Override
    public ResourceType getResourceType() {
        return ResourceType.DRF;
    }

}
