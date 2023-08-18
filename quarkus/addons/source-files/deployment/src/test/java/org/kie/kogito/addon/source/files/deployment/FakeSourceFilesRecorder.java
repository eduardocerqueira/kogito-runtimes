package org.kie.kogito.addon.source.files.deployment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kie.kogito.addon.source.files.SourceFile;
import org.kie.kogito.addon.source.files.SourceFilesRecorder;

final class FakeSourceFilesRecorder extends SourceFilesRecorder {

    private final Map<String, Collection<SourceFile>> files = new HashMap<>();

    @Override
    public void addSourceFile(String id, SourceFile sourceFile) {
        files.computeIfAbsent(id, k -> new ArrayList<>()).add(sourceFile);
    }

    boolean containsRecordFor(String processId, SourceFile sourceFile) {
        return files.getOrDefault(processId, List.of()).contains(sourceFile);
    }
}
