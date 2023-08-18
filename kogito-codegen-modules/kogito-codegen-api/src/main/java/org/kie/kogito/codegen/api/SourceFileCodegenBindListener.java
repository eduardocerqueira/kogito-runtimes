package org.kie.kogito.codegen.api;

/**
 * Interface to be notified when a codegen is bound to a source file.
 */
public interface SourceFileCodegenBindListener {

    /**
     * Called when a codegen is bound to a source file.
     * 
     * @param event the event
     */
    void onSourceFileCodegenBind(SourceFileCodegenBindEvent event);
}
