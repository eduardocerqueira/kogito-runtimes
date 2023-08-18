package org.kie.kogito.incubation.common;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class LocalUriTest {
    @Test
    public void testToString() {
        LocalUri hpath = LocalUri.Root.append("example").append("some-id").append("instances").append("some-instance-id");
        assertThat(hpath.path()).isEqualTo("/example/some-id/instances/some-instance-id");
    }

    @Test
    public void testStartsWith() {
        LocalUri hpath = LocalUri.Root.append("example").append("some-id").append("instances").append("some-instance-id");
        assertThat(hpath.startsWith("example")).isTrue();
    }

    @Test
    public void testParse() {
        String path = "/example/some-id/instances/some-instance-id";
        LocalUri hpath = LocalUri.Root.append("example").append("some-id").append("instances").append("some-instance-id");
        LocalUri parsedHPath = LocalUri.parse(path);
        assertThat(parsedHPath).isEqualTo(hpath);

    }

    @Test
    public void testParseMalformed() {
        String path = "/example////some-id//instances/some-instance-id";
        LocalUri hpath = LocalUri.Root.append("example").append("some-id").append("instances").append("some-instance-id");
        LocalUri parsedHPath = LocalUri.parse(path);
        assertThat(parsedHPath).isEqualTo(hpath);
    }

    @Test
    public void testParseMalformedRelative() {
        String path = "example////some-id//instances/some-instance-id";
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> LocalUri.parse(path));
    }

    @Test
    public void testUrlEncoding() {
        LocalUri path = LocalUri.Root.append("URL unsafe").append("??").append("Compon/ents").append("are \\ encoded");
        assertThat(path.path()).isEqualTo("/URL+unsafe/%3F%3F/Compon%2Fents/are+%5C+encoded");
    }

    @Test
    public void testUriConversion() {
        String path = "/example/some-id/instances/some-instance-id";
        LocalUri parsed = LocalUri.parse(path);
        assertThat(String.format("%s://%s", LocalUri.SCHEME, parsed.path())).isEqualTo(parsed.toUri().toString());
    }

}
