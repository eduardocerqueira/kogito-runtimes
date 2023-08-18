package org.kie.kogito.serverless.workflow.asyncapi;

import java.util.Map;

public class AsyncInfo {

    private final Map<String, AsyncChannelInfo> operation2Channel;

    public AsyncInfo(Map<String, AsyncChannelInfo> operation2Channel) {
        super();
        this.operation2Channel = operation2Channel;
    }

    public Map<String, AsyncChannelInfo> getOperation2Channel() {
        return operation2Channel;
    }

    @Override
    public String toString() {
        return "AsyncInfo [operation2Channel=" + operation2Channel + "]";
    }
}
