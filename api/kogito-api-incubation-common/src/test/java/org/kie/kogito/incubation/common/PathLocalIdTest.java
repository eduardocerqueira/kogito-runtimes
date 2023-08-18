package org.kie.kogito.incubation.common;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PathLocalIdTest {

    ExampleRoot exampleRoot = new ExampleRoot();

    @Test
    public void testId() {
        ExampleLocalId exampleLocalId = exampleRoot.get("some-id");
        assertThat(exampleLocalId.asLocalUri().path()).isEqualTo("/example/some-id");
    }

    @Test
    public void testIdNested() {
        ExampleInstanceLocalId exampleLocalId = exampleRoot.get("some-id").instances().get("some-instance-id");
        assertThat(exampleLocalId.asLocalUri().path()).isEqualTo("/example/some-id/instances/some-instance-id");
    }

    @Test
    public void testStartsWith() {
        ExampleInstanceLocalId exampleLocalId = exampleRoot.get("some-id").instances().get("some-instance-id");
        assertThat(exampleLocalId.asLocalUri().startsWith("example")).isTrue();
    }

    static class ExampleRoot implements ComponentRoot {
        public ExampleLocalId get(String identifier) {
            return new ExampleLocalId(identifier);
        }
    }

    static class ExampleLocalId extends LocalUriId {
        private static String PREFIX = "example";

        private ExampleLocalId(String identifier) {
            super(LocalUri.Root.append(PREFIX).append(identifier));
        }

        public ExampleInstanceLocalIdFactory instances() {
            return new ExampleInstanceLocalIdFactory(this);
        }

    }

    static class ExampleInstanceLocalIdFactory {
        LocalUri parent;

        public ExampleInstanceLocalIdFactory(ExampleLocalId parent) {
            this.parent = parent.asLocalUri().append("instances");
        }

        ExampleInstanceLocalId get(String identifier) {
            return new ExampleInstanceLocalId(parent, identifier);
        }
    }

    static class ExampleInstanceLocalId extends LocalUriId {

        public ExampleInstanceLocalId(LocalUri parent, String identifier) {
            super(parent.append(identifier));
        }
    }

}
