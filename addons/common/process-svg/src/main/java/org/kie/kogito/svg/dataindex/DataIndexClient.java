package org.kie.kogito.svg.dataindex;

import java.util.List;

public interface DataIndexClient {

    default String getNodeInstancesQuery(String processInstanceId) {
        return "{ ProcessInstances ( where: {   id: {  equal : \"" + processInstanceId + "\" } }) { nodes { definitionId exit } } }";
    }

    List<NodeInstance> getNodeInstancesFromProcessInstance(String processInstanceId, String authHeader);
}
