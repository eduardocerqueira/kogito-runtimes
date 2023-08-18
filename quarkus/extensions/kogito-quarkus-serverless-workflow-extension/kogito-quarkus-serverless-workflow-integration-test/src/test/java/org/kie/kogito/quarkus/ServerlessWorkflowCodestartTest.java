package org.kie.kogito.quarkus;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import io.quarkus.devtools.codestarts.quarkus.QuarkusCodestartData;
import io.quarkus.devtools.testing.codestarts.QuarkusCodestartTest;
import io.quarkus.maven.dependency.ArtifactKey;

import static io.quarkus.devtools.codestarts.quarkus.QuarkusCodestartCatalog.Language.JAVA;

public class ServerlessWorkflowCodestartTest {

    @RegisterExtension
    public static QuarkusCodestartTest codestartTest = QuarkusCodestartTest.builder()
            .setupStandaloneExtensionTest("org.kie.kogito:kogito-quarkus-serverless-workflow")
            .extension(ArtifactKey.fromString("io.quarkus:quarkus-config-yaml"))
            .putData(QuarkusCodestartData.QuarkusDataKey.APP_CONFIG, Map.of("quarkus.devservices.enabled", "false"))
            .languages(JAVA)
            .build();

    @Test
    void testContent() throws Throwable {
        codestartTest.checkGeneratedTestSource("org.acme.GreetTest");
        codestartTest.assertThatGeneratedFileMatchSnapshot(JAVA, "src/main/resources/greet.sw.json");
        codestartTest.assertThatGeneratedFileMatchSnapshot(JAVA, "src/test/resources/application.yml");
    }

    @Test
    void buildAllProjectsForLocalUse() throws Throwable {
        codestartTest.buildAllProjects();
    }

}
