package org.kie.kogito.testcontainers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.ImageNameSubstitutor;

public class KogitoImageNameSubstitutor extends ImageNameSubstitutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(KogitoImageNameSubstitutor.class);

    @Override
    public DockerImageName apply(DockerImageName original) {
        LOGGER.debug("Original Docker image used by TestContainers: {}", original);
        String canonicalName = original.asCanonicalNameString();

        if (canonicalName.startsWith("mongo:")) {
            return getMongoImageSubstitute(canonicalName);
        } else {
            return original;
        }
    }

    private DockerImageName getMongoImageSubstitute(String canonicalName) {
        return DockerImageName.parse("library/" + canonicalName).asCompatibleSubstituteFor("mongo");
    }

    @Override
    protected String getDescription() {
        return "Kogito Image Name Substitutor";
    }
}
