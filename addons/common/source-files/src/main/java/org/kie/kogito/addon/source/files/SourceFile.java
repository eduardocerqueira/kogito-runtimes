package org.kie.kogito.addon.source.files;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public final class SourceFile {

    // Serialization requires it not read-only
    private String uri;

    public SourceFile() {
        // Needed for serialization
    }

    /**
     * Creates a new SourceFile with the given URI.
     * Ex.: {@code new SourceFile("path/to/file.txt")} will create a SourceFile with URI {@code path/to/file.txt}.
     *
     * @param uri the URI of the source file
     */
    public SourceFile(String uri) {
        this.uri = Objects.requireNonNull(uri);
    }

    // Needed for serialization
    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public byte[] readContents() throws IOException {
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(getUri())) {
            if (inputStream == null) {
                throw new FileNotFoundException(getUri() + " could not be found.");
            }

            return inputStream.readAllBytes();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SourceFile that = (SourceFile) o;
        return uri.equals(that.uri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uri);
    }

    @Override
    public String toString() {
        return "SourceFile{" +
                "uri='" + uri + '\'' +
                '}';
    }
}
