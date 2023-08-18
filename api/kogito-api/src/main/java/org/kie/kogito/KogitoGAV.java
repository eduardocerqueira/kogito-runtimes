package org.kie.kogito;

import java.util.Objects;

public class KogitoGAV {

    public static final KogitoGAV EMPTY_GAV = new KogitoGAV("", "", "");

    protected String groupId;
    protected String artifactId;
    protected String version;

    protected KogitoGAV() {
        // for serialization
    }

    public KogitoGAV(final String groupId,
            final String artifactId,
            final String version) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public String getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        KogitoGAV kogitoGAV = (KogitoGAV) o;
        return Objects.equals(groupId, kogitoGAV.groupId) && Objects.equals(artifactId, kogitoGAV.artifactId) && Objects.equals(version, kogitoGAV.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, artifactId, version);
    }
}
