package org.kie.kogito.jackson.utils;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.node.ObjectNode;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonNodeVisitorTest {
    @Test
    void testSimpleObjectNodeVisitor() {
        ObjectNode source = ObjectMapperFactory.get().createObjectNode().put("name", "no javierito");
        assertThat(JsonNodeVisitor.transformTextNode(source, n -> JsonObjectUtils.fromValue("javierito"))).isEqualTo(ObjectMapperFactory.get().createObjectNode().put("name", "javierito"));
    }

    @Test
    void testComplextObjectNodeVisitor() {
        ObjectNode source = ObjectMapperFactory.get().createObjectNode().set("embedded", ObjectMapperFactory.get().createObjectNode().put("name", "no javierito"));
        assertThat(JsonNodeVisitor.transformTextNode(source, n -> JsonObjectUtils.fromValue("javierito")))
                .isEqualTo(ObjectMapperFactory.get().createObjectNode().set("embedded", ObjectMapperFactory.get().createObjectNode().put("name", "javierito")));
    }

    @Test
    void testArrayNodeVisitor() {
        ObjectNode source =
                ObjectMapperFactory.get().createObjectNode().set("embedded", ObjectMapperFactory.get().createArrayNode().add(ObjectMapperFactory.get().createObjectNode().put("name", "no javierito")));
        assertThat(JsonNodeVisitor.transformTextNode(source, n -> JsonObjectUtils.fromValue("javierito"))).isEqualTo(
                ObjectMapperFactory.get().createObjectNode().set("embedded", ObjectMapperFactory.get().createArrayNode().add(ObjectMapperFactory.get().createObjectNode().put("name", "javierito"))));
    }

    @Test
    void testStringNodeVisitor() {
        assertThat(JsonNodeVisitor.transformTextNode(JsonObjectUtils.fromValue("pepa"), p -> p).asText()).isEqualTo("pepa");
    }
}
