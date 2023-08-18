package org.kie.kogito.quarkus.serverless.workflow.asyncapi;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.kie.kogito.serverless.workflow.asyncapi.AsyncChannelInfo;
import org.kie.kogito.serverless.workflow.asyncapi.AsyncInfo;
import org.kie.kogito.serverless.workflow.asyncapi.AsyncInfoConverter;

import com.asyncapi.v2.model.AsyncAPI;
import com.asyncapi.v2.model.channel.ChannelItem;
import com.asyncapi.v2.model.channel.operation.Operation;

import io.quarkiverse.asyncapi.config.AsyncAPIRegistry;

public class AsyncAPIInfoConverter implements AsyncInfoConverter {

    private final AsyncAPIRegistry registry;

    public AsyncAPIInfoConverter(AsyncAPIRegistry registry) {
        this.registry = registry;
    }

    @Override
    public Optional<AsyncInfo> apply(String id) {
        return registry.getAsyncAPI(id).map(AsyncAPIInfoConverter::from);
    }

    private static AsyncInfo from(AsyncAPI asyncApi) {
        Map<String, AsyncChannelInfo> map = new HashMap<>();
        for (Entry<String, ChannelItem> entry : asyncApi.getChannels().entrySet()) {
            addChannel(map, entry.getValue().getPublish(), entry.getKey() + "_out", true);
            addChannel(map, entry.getValue().getSubscribe(), entry.getKey(), false);
        }
        return new AsyncInfo(map);
    }

    private static void addChannel(Map<String, AsyncChannelInfo> map, Operation operation, String channelName, boolean publish) {
        if (operation != null) {
            String operationId = operation.getOperationId();
            if (operationId != null) {
                map.putIfAbsent(operationId, new AsyncChannelInfo(channelName, publish));
            }
        }
    }
}
