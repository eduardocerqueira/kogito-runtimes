package org.kie.kogito.serverless.workflow.io;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;

public abstract class CachedContentLoader implements URIContentLoader {

    private static class NoCopyByteArrayInputStream extends ByteArrayInputStream {
        public NoCopyByteArrayInputStream(byte[] buf) {
            super(buf);
        }

        @Override
        public synchronized byte[] readAllBytes() {
            // This is an optimization that avoids copying the whole array if no byte has been read
            if (pos == 0) {
                pos = count;
                return buf;
            } else {
                return super.readAllBytes();
            }
        }
    }

    private final URI uri;

    protected CachedContentLoader(URI uri) {
        this.uri = uri;
    }

    @Override
    public InputStream getInputStream() {
        return new NoCopyByteArrayInputStream(ResourceCacheFactory.getCache().get(uri, this::loadURI));
    }

    protected abstract byte[] loadURI(URI uri);

    @Override
    public URI uri() {
        return uri;
    }
}
