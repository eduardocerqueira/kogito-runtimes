package org.kie.kogito.serverless.workflow.executor;

import org.kie.kogito.serverless.workflow.rpc.RPCWorkItemHandler;

import io.grpc.Channel;
import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;

class StaticRPCWorkItemHandler extends RPCWorkItemHandler {

    private final String name;

    public StaticRPCWorkItemHandler(String name) {
        super();
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    protected Channel getChannel(String file, String service) {
        return Grpc.newChannelBuilderForAddress(System.getProperty(getPropertyName(service, "host"), "localhost"), Integer.getInteger(getPropertyName(service, "port"), 50051),
                InsecureChannelCredentials.create()).build();
    }

    private static String getPropertyName(String service, String propName) {
        return service + "." + propName;
    }
}
