package org.kie.kogito.serverless.workflow.asyncapi;

import java.util.Optional;
import java.util.function.Function;

public interface AsyncInfoConverter extends Function<String, Optional<AsyncInfo>> {
}
