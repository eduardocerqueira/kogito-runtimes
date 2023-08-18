package org.kie.kogito.codegen.core.io;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.kie.api.io.Resource;
import org.kie.kogito.codegen.api.io.CollectedResource;

import static org.assertj.core.api.Assertions.assertThat;

class CollectedResourceProducerTest {

    @Test
    void shouldNotContainDirectories() {
        assertThat(
                CollectedResourceProducer.fromDirectory(Paths.get("src/main/resources"))
                        .stream()
                        .map(CollectedResource::resource)
                        .map(Resource::getSourcePath)
                        .map(File::new)
                        .filter(File::isDirectory)
                        .count()).isZero();
    }

    @Test
    @EnabledOnOs({ OS.LINUX, OS.MAC })
    void fromDirectoryShouldNotContainHiddenFiles() {
        assertThat(
                CollectedResourceProducer.fromDirectory(Paths.get("src/test/resources"))
                        .stream()
                        .map(CollectedResource::resource)
                        .map(Resource::getSourcePath)
                        .map(File::new)
                        .filter(f -> f.getName().contains(".gitkeep") || f.getName().contains("a-file-within-a-hidden-dir.txt") || f.getName().contains(".a-hidden-file.txt"))
                        .count()).isZero();
    }

    @Test
    @EnabledOnOs({ OS.LINUX, OS.MAC })
    void fromDirectoryInitialHiddenDirShouldNotContainFiles() {
        assertThat(CollectedResourceProducer.fromDirectory(Paths.get("src/test/resources/.hidden-dir")).size()).isZero();
    }

    @Test
    @EnabledOnOs({ OS.LINUX, OS.MAC })
    void fromFilesHiddenDirShouldNotContainFiles() {
        final Path basePath = Paths.get("src/test/resources/.hidden-dir").toAbsolutePath();
        final File hiddenFile = basePath.resolve("a-file-within-a-hidden-dir.txt").toFile();
        assertThat(CollectedResourceProducer.fromFiles(basePath, hiddenFile).size()).isZero();
    }
}