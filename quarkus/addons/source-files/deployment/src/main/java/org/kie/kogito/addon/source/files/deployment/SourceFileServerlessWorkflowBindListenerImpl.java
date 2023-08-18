package org.kie.kogito.addon.source.files.deployment;

import java.io.File;

import org.kie.kogito.addon.source.files.SourceFilesRecorder;

final class SourceFileServerlessWorkflowBindListenerImpl extends SourceFileCodegenBindListenerImpl {

    SourceFileServerlessWorkflowBindListenerImpl(File[] resourcePaths, SourceFilesRecorder sourceFilesRecorder) {
        super(resourcePaths, sourceFilesRecorder);
    }
}
