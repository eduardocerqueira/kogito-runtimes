package org.kie.kogito.codegen.api.io;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.kie.api.io.Resource;

/**
 * A (Path basePath, Resource resource) pair
 */
public class CollectedResource {

    private final Path basePath;
    private final Resource resource;

    public CollectedResource(Path basePath, Resource resource) {
        // basePath must be a prefix of sourcePath
        // unless it is a jar file, then the check is ignored
        try {
            if (!basePath.toString().endsWith(".jar") &&
                    !Paths.get(resource.getSourcePath()).toAbsolutePath().toRealPath()
                            .startsWith(basePath.toAbsolutePath().toRealPath())) {
                throw new IllegalArgumentException(
                        String.format("basePath %s is not a prefix to the resource sourcePath %s",
                                basePath, resource.getSourcePath()));
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(
                    String.format("Could not determine if basePath %s is a prefix to the resource sourcePath %s",
                            basePath, resource.getSourcePath()),
                    e);
        }
        this.basePath = basePath;
        this.resource = resource;
    }

    public Path basePath() {
        return basePath;
    }

    public Resource resource() {
        return resource;
    }
}
