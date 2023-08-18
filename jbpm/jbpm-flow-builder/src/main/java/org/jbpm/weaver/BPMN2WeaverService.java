package org.jbpm.weaver;

import org.kie.api.io.ResourceType;

public class BPMN2WeaverService extends AbstractWeaverService {

    @Override
    public ResourceType getResourceType() {
        return ResourceType.BPMN2;
    }

}
