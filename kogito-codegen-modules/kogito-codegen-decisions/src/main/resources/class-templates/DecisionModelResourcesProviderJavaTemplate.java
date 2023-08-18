package $Package$;

public class DecisionModelResourcesProvider implements org.kie.kogito.decision.DecisionModelResourcesProvider {

    // See https://issues.redhat.com/browse/KOGITO-3330
    private static java.io.InputStreamReader readResource(java.io.InputStream stream) {
        if (org.kie.kogito.internal.RuntimeEnvironment.isJdk()) {
            return new java.io.InputStreamReader(stream);
        }

        try {
            byte[] bytes = org.drools.util.IoUtils.readBytesFromInputStream(stream);
            java.io.ByteArrayInputStream byteArrayInputStream = new java.io.ByteArrayInputStream(bytes);
            return new java.io.InputStreamReader(byteArrayInputStream);
        } catch (java.io.IOException e) {
            throw new java.io.UncheckedIOException(e);
        }
    }

    private final static java.util.List<org.kie.kogito.decision.DecisionModelResource> resources = getResources();

    @Override
    public java.util.List<org.kie.kogito.decision.DecisionModelResource> get() {
        return this.resources;
    }

    private final static java.util.List<org.kie.kogito.decision.DecisionModelResource> getResources() {
        java.util.List<org.kie.kogito.decision.DecisionModelResource> resourcePaths = new java.util.ArrayList<>();
        return resourcePaths;
    }

}
