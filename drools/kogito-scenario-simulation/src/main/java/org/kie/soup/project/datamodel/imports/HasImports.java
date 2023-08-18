package org.kie.soup.project.datamodel.imports;

/**
 * Copied from kie-soup to avoid the dependency
 */
public interface HasImports {

    Imports getImports();

    void setImports(final Imports imports);

}
