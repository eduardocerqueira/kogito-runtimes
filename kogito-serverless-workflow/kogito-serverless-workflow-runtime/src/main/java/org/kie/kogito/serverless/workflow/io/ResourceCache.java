package org.kie.kogito.serverless.workflow.io;

import java.net.URI;
import java.util.function.Function;

public interface ResourceCache {
    byte[] get(URI uri, Function<URI, byte[]> retrieveCall);
}
