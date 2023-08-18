package org.kie.kogito.internal;

import java.nio.file.Path;
import java.util.Set;

public class SupportedExtensions {

    private static final Set<String> BPMN_EXTENSIONS = Set.of(".bpmn", ".bpmn2");
    private static final Set<String> SWF_EXTENSIONS = Set.of(".sw.yml", ".sw.yaml", ".sw.json");

    public static boolean isSourceFile(Path file) {
        return isSourceFile(file.toString());
    }

    public static boolean isSourceFile(String file) {
        return BPMN_EXTENSIONS.stream().anyMatch(file::endsWith)
                || SWF_EXTENSIONS.stream().anyMatch(file::endsWith);
    }

    public static Set<String> getBPMNExtensions() {
        return BPMN_EXTENSIONS;
    }

    public static Set<String> getSWFExtensions() {
        return SWF_EXTENSIONS;
    }

    private SupportedExtensions() {
    }
}
