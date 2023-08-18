package org.kie.kogito.serverless.workflow.asyncapi;

import java.util.Optional;

public interface AsyncInfoResolver {
    Optional<AsyncInfo> getAsyncInfo(String id);
}
