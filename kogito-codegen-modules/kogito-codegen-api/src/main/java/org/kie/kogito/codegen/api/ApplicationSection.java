package org.kie.kogito.codegen.api;

import com.github.javaparser.ast.CompilationUnit;

/**
 * A descriptor for a "section" of the root Application class.
 * It contains a factory method for the section (which is an object instance)
 * and the corresponding class.
 *
 * This is to allow the pattern:
 * app.$sectionname().$method()
 *
 * e.g.:
 * app.get(Processes.class).createMyProcess()
 */
public interface ApplicationSection {

    String sectionClassName();

    CompilationUnit compilationUnit();

}
