package org.kie.kogito.serverless.workflow.io;

import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URI;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class FallbackContentLoader extends CachedContentLoader {

    private static final Logger logger = LoggerFactory.getLogger(FallbackContentLoader.class);

    private final Optional<URIContentLoader> fallbackLoader;

    protected FallbackContentLoader(URI uri, Optional<URIContentLoader> fallbackContentLoader) {
        super(uri);
        this.fallbackLoader = fallbackContentLoader;
    }

    @Override
    public InputStream getInputStream() {
        try {
            return super.getInputStream();
        } catch (UncheckedIOException io) {
            try {
                return fallbackLoader.orElseThrow(() -> io).getInputStream();
            } catch (UncheckedIOException | IllegalArgumentException io2) {
                logger.error("Fallback loader failed with message \"{}\", throwing original exception", io2.getMessage());
                throw io;
            }
        }
    }
}
