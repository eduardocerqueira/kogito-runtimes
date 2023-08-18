package org.kie.kogito.addon.source.files;

import java.util.Collection;
import java.util.Optional;

public interface SourceFilesProvider {

    /**
     * Returns the source file that has the specified URI.
     * 
     * @param uri the URI
     * @return the source file
     */
    Optional<SourceFile> getSourceFilesByUri(String uri);

    /**
     * Returns the source files for the given processId.
     * 
     * @param processId the process identifier
     * @return the source files collection. The collection may be empty but not null.
     */
    Collection<SourceFile> getProcessSourceFiles(String processId);

    /**
     * Returns the source file for the given processId.
     *
     * @param processId the process identifier
     * @return the source file.
     */
    Optional<SourceFile> getProcessSourceFile(String processId);
}
