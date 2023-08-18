package org.kie.kogito.quarkus.workflows;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.kie.kogito.serverless.workflow.rpc.RPCWorkItemHandler;

import io.quarkus.test.junit.QuarkusTestProfile;

public class EmitEnumProfile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
        return Collections.singletonMap(RPCWorkItemHandler.GRPC_ENUM_DEFAULT_PROPERTY, "true");
    }

    @Override
    public Set<String> tags() {
        return Collections.singleton("emitEnum");
    }
}
