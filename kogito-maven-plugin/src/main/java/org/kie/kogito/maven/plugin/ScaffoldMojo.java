package org.kie.kogito.maven.plugin;

import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

@Mojo(name = "scaffold",
        requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME,
        requiresProject = true,
        threadSafe = true)
public class ScaffoldMojo extends GenerateModelMojo {

    @Parameter(property = "kogito.codegen.ondemand", defaultValue = "true")
    private boolean onDemand;

    @Parameter(property = "kogito.codegen.sources.directory", defaultValue = "${project.build.sourceDirectory}")
    private File customizableSources;

    @Override
    public void execute() throws MojoExecutionException {
        addCompileSourceRoots();
        generateModel();
    }

    @Override
    public boolean isOnDemand() {
        return onDemand;
    }

    @Override
    protected File getSourcesPath() {
        return customizableSources;
    }
}
