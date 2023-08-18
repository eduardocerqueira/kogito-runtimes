package org.kie.kogito.maven.plugin.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.artifact.resolver.filter.CumulativeScopeArtifactFilter;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.drools.compiler.kie.builder.impl.InternalKieModule;
import org.drools.compiler.kie.builder.impl.ZipKieModule;
import org.drools.compiler.kproject.models.KieModuleModelImpl;
import org.kie.api.builder.ReleaseId;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.util.maven.support.ReleaseIdImpl;

import static org.drools.compiler.kie.builder.impl.KieBuilderImpl.setDefaultsforEmptyKieModule;

public final class MojoUtil {

    public static Set<URL> getProjectFiles(final MavenProject mavenProject,
            final List<InternalKieModule> kmoduleDeps)
            throws DependencyResolutionRequiredException, IOException {
        final Set<URL> urls = new HashSet<>();
        for (final String element : mavenProject.getCompileClasspathElements()) {
            urls.add(new File(element).toURI().toURL());
        }

        mavenProject.setArtifactFilter(new CumulativeScopeArtifactFilter(Arrays.asList("compile", "runtime")));
        for (final Artifact artifact : mavenProject.getArtifacts()) {
            if (artifact.getType().equals("jar")) {
                populateURLsFromJarArtifact(urls, artifact, kmoduleDeps);
            }
        }
        return urls;
    }

    public static ClassLoader createProjectClassLoader(final ClassLoader parentClassLoader,
            final MavenProject mavenProject,
            final File outputDirectory,
            final List<InternalKieModule> kmoduleDeps) throws MojoExecutionException {
        try {
            final Set<URL> urls = getProjectFiles(mavenProject, kmoduleDeps);
            urls.add(outputDirectory.toURI().toURL());

            return URLClassLoader.newInstance(urls.toArray(new URL[0]), parentClassLoader);
        } catch (final DependencyResolutionRequiredException | IOException e) {
            throw new MojoExecutionException("Error setting up Kie ClassLoader", e);
        }
    }

    private static void populateURLsFromJarArtifact(final Set<URL> toPopulate, final Artifact artifact,
            final List<InternalKieModule> kmoduleDeps) throws IOException {
        final File file = artifact.getFile();
        if (file != null && file.isFile()) {
            toPopulate.add(file.toURI().toURL());
            final KieModuleModel depModel = getDependencyKieModel(file);
            if (kmoduleDeps != null && depModel != null) {
                final ReleaseId releaseId = new ReleaseIdImpl(artifact.getGroupId(), artifact.getArtifactId(),
                        artifact.getVersion());
                kmoduleDeps.add(new ZipKieModule(releaseId, depModel, file));
            }
        }
    }

    private static KieModuleModel getDependencyKieModel(final File jar) throws IOException {
        try (final ZipFile zipFile = new ZipFile(jar)) {
            final ZipEntry zipEntry = zipFile.getEntry(KieModuleModelImpl.KMODULE_JAR_PATH.asString());
            if (zipEntry != null) {
                final KieModuleModel kieModuleModel = KieModuleModelImpl.fromXML(zipFile.getInputStream(zipEntry));
                setDefaultsforEmptyKieModule(kieModuleModel);
                return kieModuleModel;
            }
        }
        return null;
    }

    private MojoUtil() {
        // Creating instances of util classes is forbidden.
    }
}
