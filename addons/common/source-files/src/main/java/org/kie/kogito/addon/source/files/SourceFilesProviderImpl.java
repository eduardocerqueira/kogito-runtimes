package org.kie.kogito.addon.source.files;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.kie.kogito.internal.SupportedExtensions;

public final class SourceFilesProviderImpl implements SourceFilesProvider {

    private final Map<String, Collection<SourceFile>> sourceFiles = new HashMap<>();

    public void addSourceFile(String id, SourceFile sourceFile) {
        sourceFiles.computeIfAbsent(id, k -> new HashSet<>()).add(sourceFile);
    }

    @Override
    public Optional<SourceFile> getSourceFilesByUri(String uri) {
        return sourceFiles.values().stream()
                .flatMap(Collection::stream)
                .filter(file -> Objects.equals(file.getUri(), uri))
                .findFirst();
    }

    @Override
    public Collection<SourceFile> getProcessSourceFiles(String processId) {
        return sourceFiles.getOrDefault(processId, Set.of());
    }

    @Override
    public Optional<SourceFile> getProcessSourceFile(String processId) throws SourceFilesException {
        return getProcessSourceFiles(processId).stream()
                .filter(this::isValidDefinitionSource)
                .findFirst();
    }

    private boolean isValidDefinitionSource(SourceFile sourceFile) {
        return SupportedExtensions.isSourceFile(sourceFile.getUri());
    }
}
