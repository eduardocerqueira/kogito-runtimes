package org.kie.kogito.serverless.workflow.asyncapi;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class CachedAsyncInfoResolver implements AsyncInfoResolver {

    private final Map<String, Optional<AsyncInfo>> asyncInfos = new ConcurrentHashMap<>();

    private final Optional<AsyncInfoConverter> converter;

    public CachedAsyncInfoResolver(AsyncInfoConverter converter) {
        this.converter = Optional.ofNullable(converter);
    }

    @Override
    public Optional<AsyncInfo> getAsyncInfo(String id) {
        return converter.flatMap(c -> asyncInfos.computeIfAbsent(id, c));
    }
}
