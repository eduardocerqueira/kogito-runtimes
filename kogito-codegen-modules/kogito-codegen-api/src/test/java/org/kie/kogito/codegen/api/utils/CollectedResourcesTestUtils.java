package org.kie.kogito.codegen.api.utils;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.drools.io.FileSystemResource;
import org.kie.kogito.codegen.api.io.CollectedResource;

public final class CollectedResourcesTestUtils {

    private static final String TEST_RESOURCE_PATH = "src/test/resources";

    private CollectedResourcesTestUtils() {

    }

    /**
     * @see #toCollectedResources(String...)
     */
    public static CollectedResource toCollectedResource(String path) {
        return new CollectedResource(Paths.get(TEST_RESOURCE_PATH + path), new FileSystemResource(TEST_RESOURCE_PATH + path));
    }

    /**
     * Transform Test Resources in {@link CollectedResource} to be used in unit tests.
     *
     * @param paths relative to "src/test/resources". For example "/myfile.bpmn".
     * @return A list of {@link CollectedResource}
     */
    public static List<CollectedResource> toCollectedResources(String... paths) {
        return Arrays.stream(paths).map(p -> new CollectedResource(Paths.get(TEST_RESOURCE_PATH + p), new FileSystemResource(TEST_RESOURCE_PATH + p))).collect(Collectors.toList());
    }
}
