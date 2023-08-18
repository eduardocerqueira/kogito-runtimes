package org.kie.kogito.addon.source.files.deployment;

import java.io.File;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.kie.kogito.addon.source.files.SourceFile;
import org.kie.kogito.codegen.api.SourceFileCodegenBindEvent;

import static org.assertj.core.api.Assertions.assertThat;

class SourceFileCodegenBindListenerImplTest {

    public static Stream<Arguments> testOnSourceFileProcessBindEventSources() {
        return Stream.of(
                Arguments.arguments("/dev/proj/other_resources/org/acme/process/a_process.bpmn", "org/acme/process/a_process.bpmn"),
                Arguments.arguments("file://restcountries.json", "file://restcountries.json"),
                Arguments.arguments("/a/random/directory/a_process.bpmn", "/a/random/directory/a_process.bpmn"));
    }

    @ParameterizedTest
    @MethodSource("testOnSourceFileProcessBindEventSources")
    void testOnSourceFileProcessBindEvent(String eventSourceFile, String expectedSourceFile) {
        File[] resourcePaths = new File[] { new File("/dev/proj/resources/"), new File("/dev/proj/other_resources/") };

        String processId = "a_process";

        SourceFileCodegenBindEvent event = new SourceFileCodegenBindEvent(processId, eventSourceFile);

        FakeSourceFilesRecorder sourceFilesRecorder = new FakeSourceFilesRecorder();

        new SourceFileProcessBindListenerImpl(resourcePaths, sourceFilesRecorder).onSourceFileCodegenBind(event);

        assertThat(sourceFilesRecorder.containsRecordFor(processId, new SourceFile(expectedSourceFile))).isTrue();
    }
}
