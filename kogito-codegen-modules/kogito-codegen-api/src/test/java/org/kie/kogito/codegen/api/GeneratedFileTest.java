package org.kie.kogito.codegen.api;

import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.drools.codegen.common.GeneratedFile;
import org.drools.codegen.common.GeneratedFileType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GeneratedFileTest {

    private static final GeneratedFileType TEST_TYPE = GeneratedFileType.SOURCE;
    private static final String TEST_RELATIVE_PATH = "relativePath";
    private static final byte[] TEST_CONTENTS = "testContents".getBytes(StandardCharsets.UTF_8);

    private static List<GeneratedFile> testFiles = new ArrayList<>();

    @BeforeAll
    public static void createTestFile() {
        testFiles.add(new GeneratedFile(TEST_TYPE, TEST_RELATIVE_PATH, TEST_CONTENTS));
        testFiles.add(new GeneratedFile(TEST_TYPE, TEST_RELATIVE_PATH, new String(TEST_CONTENTS)));
        testFiles.add(new GeneratedFile(TEST_TYPE, Paths.get(TEST_RELATIVE_PATH), TEST_CONTENTS));
        testFiles.add(new GeneratedFile(TEST_TYPE, Paths.get(TEST_RELATIVE_PATH), new String(TEST_CONTENTS)));
    }

    @Test
    public void relativePath() {
        testFiles.forEach(testFile -> assertThat(testFile.relativePath()).isEqualTo(TEST_RELATIVE_PATH));
    }

    @Test
    public void contents() {
        testFiles.forEach(testFile -> assertThat(testFile.contents()).isEqualTo(TEST_CONTENTS));
    }

    @Test
    public void type() {
        testFiles.forEach(testFile -> assertThat(testFile.type()).isEqualTo(TEST_TYPE));
    }

    @Test
    public void category() {
        testFiles.forEach(testFile -> assertThat(testFile.category()).isEqualTo(TEST_TYPE.category()));
    }

    @Test
    public void equals() {
        GeneratedFile sample = new GeneratedFile(TEST_TYPE, TEST_RELATIVE_PATH, TEST_CONTENTS);
        testFiles.forEach(testFile -> assertThat(testFile).isEqualTo(sample));
    }
}
