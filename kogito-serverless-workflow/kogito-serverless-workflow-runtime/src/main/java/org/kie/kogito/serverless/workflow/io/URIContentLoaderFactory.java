package org.kie.kogito.serverless.workflow.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URI;
import java.util.Optional;

import io.serverlessworkflow.api.Workflow;

public class URIContentLoaderFactory {

    public static byte[] readAllBytes(URIContentLoader loader) {
        try (InputStream is = loader.getInputStream()) {
            return is.readAllBytes();
        } catch (IOException io) {
            throw new UncheckedIOException(io);
        }
    }

    public static URIContentLoader runtimeLoader(String uriStr) {
        URI uri = URI.create(uriStr);
        Builder builder = new Builder(uri);
        builder.withFallback(new ClassPathContentLoader(uri, Optional.empty()));
        return builder.build();

    }

    public static URIContentLoader buildLoader(URI uri, ClassLoader cl, Workflow workflow, String authRef) {
        return new Builder(uri).withClassloader(cl).withWorkflow(workflow).withAuthRef(authRef).build();
    }

    /**
     * @deprecated Use builder
     */
    @Deprecated
    public static URIContentLoader loader(URI uri, Optional<ClassLoader> cl, Optional<URIContentLoader> fallback, Optional<Workflow> workflow, String authRef) {
        Builder builder = new Builder(uri);
        cl.ifPresent(builder::withClassloader);
        fallback.ifPresent(builder::withFallback);
        workflow.ifPresent(builder::withWorkflow);
        builder.withAuthRef(authRef);
        return builder.build();
    }

    public static Builder builder(URI uri) {
        return new Builder(uri);
    }

    public static class Builder {
        private URI uri;
        private ClassLoader cl;
        private URIContentLoader fallback;
        private Workflow workflow;
        private String authRef;
        private String baseURI;

        private Builder(URI uri) {
            this.uri = uri;
        }

        public Builder withClassloader(ClassLoader cl) {
            this.cl = cl;
            return this;
        }

        public Builder withFallback(URIContentLoader fallback) {
            this.fallback = fallback;
            return this;
        }

        public Builder withWorkflow(Workflow workflow) {
            this.workflow = workflow;
            return this;
        }

        public Builder withAuthRef(String authRef) {
            this.authRef = authRef;
            return this;
        }

        public Builder withBaseURI(String baseURI) {
            this.baseURI = baseURI;
            return this;
        }

        public URIContentLoader build() {
            if (uri.getScheme() == null) {
                if (baseURI != null) {
                    uri = URI.create(baseURI + "/" + uri.toString());
                } else {
                    return new ClassPathContentLoader(uri, Optional.ofNullable(cl));
                }
            }
            switch (URIContentLoaderType.from(uri)) {
                case FILE:
                    return new FileContentLoader(uri, Optional.ofNullable(fallback));
                case HTTP:
                    return new HttpContentLoader(uri, Optional.ofNullable(fallback), Optional.ofNullable(workflow), authRef);
                default:
                case CLASSPATH:
                    return new ClassPathContentLoader(uri, Optional.ofNullable(cl));
            }
        }
    }

    private URIContentLoaderFactory() {
    }
}
