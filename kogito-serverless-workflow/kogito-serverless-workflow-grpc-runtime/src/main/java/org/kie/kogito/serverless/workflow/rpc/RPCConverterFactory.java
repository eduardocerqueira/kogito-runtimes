package org.kie.kogito.serverless.workflow.rpc;

public class RPCConverterFactory {
    private RPCConverterFactory() {
    }

    private static final RPCConverter instance = new ProtobufUtilRPCConverter();

    public static RPCConverter get() {
        return instance;
    }
}
