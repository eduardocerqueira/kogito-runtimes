package org.kie.kogito.serverless.workflow.io;

import java.io.IOException;
import java.io.UncheckedIOException;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.kie.kogito.serverless.workflow.io.URIContentLoaderFactory.readAllBytes;
import static org.kie.kogito.serverless.workflow.io.URIContentLoaderFactory.runtimeLoader;

class URIContentLoaderTest {

    @Test
    void testExistingFile() throws IOException {
        assertThat(new String(readAllBytes(runtimeLoader("file:/pepe.txt")))).isEqualTo("my name is javierito");
    }

    @Test
    void testExistingClasspath() throws IOException {
        assertThat(new String(readAllBytes(runtimeLoader("classpath:/pepe.txt")))).isEqualTo("my name is javierito");
    }

    @Test
    void testNotExistingFile() {
        assertThatExceptionOfType(UncheckedIOException.class).isThrownBy(() -> readAllBytes(runtimeLoader("file:/noPepe.txt")));
    }

    @Test
    void testNotExistingClasspath() {
        assertThatIllegalArgumentException().isThrownBy(() -> readAllBytes(runtimeLoader("classpath:/noPepe.txt")));
    }

}
