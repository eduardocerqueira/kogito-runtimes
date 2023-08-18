package org.kie.kogito.codegen.api.utils;

public class KogitoCodeGenConstants {

    private KogitoCodeGenConstants() {

    }

    public static final String VALIDATION_CLASS = "javax.validation.constraints.NotNull";
    public static final String OPENAPI_SPEC_CLASS = "org.eclipse.microprofile.openapi.annotations.media.Schema";
    /**
     * Property that controls whether Kogito Codegen should ignore hidden files. Defaults to true.
     */
    public static final String IGNORE_HIDDEN_FILES_PROP = "kogito.codegen.ignoreHiddenFiles";
}
