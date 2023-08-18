package org.kie.kogito.addon.source.files.deployment;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;

import org.kie.kogito.addon.source.files.SourceFile;
import org.kie.kogito.addon.source.files.SourceFilesRecorder;
import org.kie.kogito.codegen.api.SourceFileCodegenBindEvent;
import org.kie.kogito.codegen.api.SourceFileCodegenBindListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class SourceFileCodegenBindListenerImpl implements SourceFileCodegenBindListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(SourceFileCodegenBindListenerImpl.class);

    private final File[] resourcePaths;

    private final SourceFilesRecorder sourceFilesRecorder;

    protected SourceFileCodegenBindListenerImpl(File[] resourcePaths, SourceFilesRecorder sourceFilesRecorder) {
        this.resourcePaths = resourcePaths;
        this.sourceFilesRecorder = sourceFilesRecorder;
    }

    @Override
    public void onSourceFileCodegenBind(SourceFileCodegenBindEvent event) {
        LOGGER.debug("Received event {}", event);

        Path sourceFilePath = Path.of(event.getUri());

        Arrays.stream(resourcePaths)
                .map(File::toPath)
                .filter(sourceFilePath::startsWith)
                .findFirst()
                .ifPresentOrElse(resourcePath -> {
                    SourceFile sourceFile = new SourceFile(resolveSourceFilePath(sourceFilePath, resourcePath));
                    sourceFilesRecorder.addSourceFile(event.getSourceFileId(), sourceFile);
                }, () -> sourceFilesRecorder.addSourceFile(event.getSourceFileId(), new SourceFile(event.getUri())));
    }

    private String resolveSourceFilePath(Path sourceFilePath, Path locationPath) {
        return sourceFilePath.subpath(locationPath.getNameCount(), sourceFilePath.getNameCount()).toString();
    }
}
