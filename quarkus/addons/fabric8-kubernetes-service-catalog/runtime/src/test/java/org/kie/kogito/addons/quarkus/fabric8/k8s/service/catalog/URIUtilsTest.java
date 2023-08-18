package org.kie.kogito.addons.quarkus.fabric8.k8s.service.catalog;

import java.net.URI;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class URIUtilsTest {

    @Test
    public void testURI() {
        assertEquals(URI.create("http://test:8009"), URIUtils.builder("http", 8009, "test").get());
    }

    @Test
    public void testInvalidURI() {
        assertEquals(Optional.empty(), URIUtils.builder("http", -1, ""));
    }
}
