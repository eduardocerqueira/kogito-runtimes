package org.kie.kogito.addons.quarkus.fabric8.k8s.service.catalog;

import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class URIUtils {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getName());

    private URIUtils() {
    }

    static Optional<URI> builder(String scheme, int port, String host) {
        try {
            logger.debug("Using scheme [{}], port[{}] and host[{}] to build the target service uri.",
                    scheme, port, host);
            return Optional.of(new URI(scheme, null, host, port, null, null, null));
        } catch (Exception e) {
            logger.warn("Failed to parser URI {}", e.getMessage());
        }
        return Optional.empty();
    }

}
