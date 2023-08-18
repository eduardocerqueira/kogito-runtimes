package org.kie.kogito.serverless.workflow.io;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class FileContentLoader extends FallbackContentLoader {

    private final Path path;

    public FileContentLoader(URI uri, Optional<URIContentLoader> fallbackLoader) {
        super(uri, fallbackLoader);
        this.path = Path.of(uri);
    }

    public Path getPath() {
        return path;
    }

    @Override
    public URIContentLoaderType type() {
        return URIContentLoaderType.FILE;
    }

    @Override
    protected byte[] loadURI(URI uri) {
        try {
            return Files.readAllBytes(path);
        } catch (IOException io) {
            throw new UncheckedIOException(io);
        }
    }
}
