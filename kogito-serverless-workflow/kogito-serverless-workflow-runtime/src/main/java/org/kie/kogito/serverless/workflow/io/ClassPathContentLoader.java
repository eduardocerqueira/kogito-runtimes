package org.kie.kogito.serverless.workflow.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;

public class ClassPathContentLoader extends CachedContentLoader {

    private final Optional<URL> resource;
    private final String path;

    public ClassPathContentLoader(URI uri, Optional<ClassLoader> cl) {
        super(uri);
        this.path = getPath(uri);
        this.resource = Optional.ofNullable(cl.orElse(Thread.currentThread().getContextClassLoader()).getResource(path));
    }

    private static String getPath(URI uri) {
        String path = uri.getPath();
        Objects.requireNonNull(path, "classpath cannot be null");
        while (path.startsWith("/")) {
            path = path.substring(1);
        }
        return path;
    }

    public Optional<URL> getResource() {
        return resource;
    }

    @Override
    protected byte[] loadURI(URI uri) {
        return resource.map(this::loadBytes).orElseThrow(() -> new IllegalArgumentException("cannot find classpath resource " + path));
    }

    private byte[] loadBytes(URL r) {
        try (InputStream is = r.openStream()) {
            return is.readAllBytes();
        } catch (IOException io) {
            throw new UncheckedIOException(io);
        }
    }

    @Override
    public URIContentLoaderType type() {
        return URIContentLoaderType.CLASSPATH;
    }
}
