package org.kie.kogito.quarkus.dmn;

import java.util.Map;
import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import io.quarkus.devtools.codestarts.quarkus.QuarkusCodestartData.QuarkusDataKey;
import io.quarkus.devtools.testing.codestarts.QuarkusCodestartTest;
import io.quarkus.maven.ArtifactCoords;
import io.quarkus.maven.ArtifactKey;

import static io.quarkus.devtools.codestarts.quarkus.QuarkusCodestartCatalog.Language.JAVA;

public class KogitoDMNCodeCodestartIT {

    static final Properties properties = new Properties();
    static {
        try {
            properties.load(KogitoDMNCodeCodestartIT.class.getClassLoader().getResourceAsStream("project.properties"));
        } catch (Exception e) {
            throw new RuntimeException("project.properties version unknown");
        }
    }

    public static String assertjVersion() {
        return properties.getProperty("version.assertj");
    }

    public static String projectVersion() {
        return properties.getProperty("version");
    }

    @RegisterExtension
    public static QuarkusCodestartTest codestartTest = QuarkusCodestartTest.builder()
            //.setupStandaloneExtensionTest("org.kie.kogito:kogito-quarkus-decisions") //TODO Revert back once Quarkus LTS is upgraded to 2.10+
            .standaloneExtensionCatalog()
            .extension(ArtifactCoords.fromString("org.kie.kogito:kogito-quarkus-decisions:" + projectVersion()))
            .extension(ArtifactKey.fromString("io.quarkus:quarkus-resteasy-jackson")) // account for KOGITO-5817
            .extension(ArtifactCoords.fromString("org.assertj:assertj-core:" + assertjVersion()))
            .putData(QuarkusDataKey.APP_CONFIG, Map.of("quarkus.http.test-port", "0"))
            .languages(JAVA)
            .build();

    @Test
    void testContent() throws Throwable {
        codestartTest.checkGeneratedTestSource("org.acme.PricingTest");
    }

    @Test
    void testBuild() throws Throwable {
        codestartTest.buildAllProjects();
    }
}
