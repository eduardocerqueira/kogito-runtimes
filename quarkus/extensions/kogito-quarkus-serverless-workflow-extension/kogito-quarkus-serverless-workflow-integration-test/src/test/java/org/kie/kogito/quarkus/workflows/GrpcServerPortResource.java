package org.kie.kogito.quarkus.workflows;

import java.util.Map;

import org.apache.groovy.util.Maps;
import org.kie.kogito.test.utils.SocketUtils;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class GrpcServerPortResource implements QuarkusTestResourceLifecycleManager {

    @Override
    public Map<String, String> start() {
        String port = Integer.toString(SocketUtils.findAvailablePort());
        return Maps.of("quarkus.grpc.clients.Greeter.port", port,
                "quarkus.grpc.server.port", port,
                "quarkus.grpc.server.test-port", port);
    }

    @Override
    public void stop() {
    }
}
