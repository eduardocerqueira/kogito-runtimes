package org.kie.kogito.svg;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.kie.kogito.test.utils.CustomSVGDiffer;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.Diff;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public abstract class ProcessSvgServiceTest {

    private final static String PROCESS_ID = "travels";

    public static String readFileContent(String file) throws URISyntaxException, IOException {
        Path path = Paths.get(Thread.currentThread().getContextClassLoader().getResource(file).toURI());
        return new String(Files.readAllBytes(path));
    }

    @Test
    public void getProcessSvgWithoutSvgResourcePathTest() throws Exception {
        String fileContent = getTravelsSVGFile();
        Optional<String> svgContent = getTestedProcessSvgService().getProcessSvg(PROCESS_ID);
        assertThat(svgContent).isPresent().hasValue(fileContent);
    }

    @Test
    public void getProcessSvgFromFileSystemSuccessTest() throws Exception {
        String fileContent = getTravelsSVGFile();
        getTestedProcessSvgService().setSvgResourcesPath(Optional.of("./src/test/resources/META-INF/processSVG/"));
        Optional<String> svgContent = getTestedProcessSvgService().getProcessSvg(PROCESS_ID);
        assertThat(svgContent).isPresent().hasValue(fileContent);
    }

    @Test
    public void getProcessSvgFromFileSystemFailTest() throws Exception {
        getTestedProcessSvgService().setSvgResourcesPath(Optional.of("./src/test/resources/META-INF/processSVG/"));
        assertThat(getTestedProcessSvgService().getProcessSvg("UnexistingProcessId")).isEmpty();
    }

    @Test
    public void annotateExecutedPathTest() throws Exception {
        final String content = getTestedProcessSvgService().annotateExecutedPath(
                getTravelsSVGFile(),
                Arrays.asList("_1A708F87-11C0-42A0-A464-0B7E259C426F"),
                Collections.emptyList()).get();

        Diff myDiff = new CustomSVGDiffer(content).withTest(Input.fromString(readFileContent("travels-expected.svg")));

        assertThat(myDiff.hasDifferences()).isFalse();

        assertThat(getTestedProcessSvgService().annotateExecutedPath(
                null,
                Arrays.asList("_1A708F87-11C0-42A0-A464-0B7E259C426F"),
                Collections.emptyList())).isEmpty();
        assertThat(getTestedProcessSvgService().annotateExecutedPath(
                getTravelsSVGFile(),
                Collections.emptyList(),
                Collections.emptyList())).hasValue(getTravelsSVGFile());
    }

    @Test
    public void readFileFromClassPathTest() throws Exception {
        assertThat(getTestedProcessSvgService().readFileContentFromClassPath("undefined")).isEmpty();
        assertThat(getTravelsSVGFile()).isEqualTo(getTestedProcessSvgService().readFileContentFromClassPath("travels.svg").get());
    }

    @Test
    public void testWrongSVGContentThrowsException() {
        AbstractProcessSvgService testedProcessSvgService = getTestedProcessSvgService();
        List completedNodes = Arrays.asList("_1A708F87-11C0-42A0-A464-0B7E259C426F");
        List activeNodes = Collections.emptyList();
        try {
            testedProcessSvgService.annotateExecutedPath("wrongSVGContent", completedNodes, activeNodes);
            fail("Expected an ProcessSVGException to be thrown");
        } catch (ProcessSVGException e) {
            assertThat(e.getMessage()).isEqualTo("Failed to annotated SVG for process instance");
        }
    }

    public String getTravelsSVGFile() throws Exception {
        return readFileContent("META-INF/processSVG/travels.svg");
    }

    protected abstract AbstractProcessSvgService getTestedProcessSvgService();
}
