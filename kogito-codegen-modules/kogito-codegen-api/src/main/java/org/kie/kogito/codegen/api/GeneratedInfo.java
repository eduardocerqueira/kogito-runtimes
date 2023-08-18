package org.kie.kogito.codegen.api;

import java.util.Collection;
import java.util.Collections;

import org.drools.codegen.common.GeneratedFile;

/**
 * A wrapper that allows a generator to return a core information structure, like process, plus an additional
 * set of files to be generated
 * 
 * @param <T> the type of the object returned by info
 */
public class GeneratedInfo<T> {
    private T info;
    private Collection<GeneratedFile> files;

    public GeneratedInfo(T info) {
        this(info, Collections.emptyList());
    }

    public GeneratedInfo(T process, Collection<GeneratedFile> files) {
        this.info = process;
        this.files = files;
    }

    public T info() {
        return info;
    }

    public Collection<GeneratedFile> files() {
        return files;
    }
}
