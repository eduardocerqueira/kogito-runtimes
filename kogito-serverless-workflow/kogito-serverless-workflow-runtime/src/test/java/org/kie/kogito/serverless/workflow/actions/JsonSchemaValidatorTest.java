package org.kie.kogito.serverless.workflow.actions;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.kie.kogito.jackson.utils.ObjectMapperFactory;
import org.kie.kogito.serverless.workflow.SWFConstants;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class JsonSchemaValidatorTest {

    private static JsonSchemaValidator validator;

    @BeforeAll
    static void init() {
        validator = new JsonSchemaValidator("expression.json", true);
    }

    @Test
    void testValidSchema() throws IOException {
        final Map<String, Object> model = Collections.singletonMap(SWFConstants.DEFAULT_WORKFLOW_VAR, createNode(new IntNode(4), new IntNode(3)));
        assertThatNoException().isThrownBy(() -> validator.validate(model));
    }

    @Test
    void testInvalidSchema() throws IOException {
        final Map<String, Object> model = Collections.singletonMap(SWFConstants.DEFAULT_WORKFLOW_VAR, createNode(new TextNode("xcdsfd"), new IntNode(3)));
        assertThatIllegalArgumentException().isThrownBy(() -> validator.validate(model));
    }

    @Test
    void testEmptyInput() throws IOException {
        assertThatIllegalArgumentException().isThrownBy(() -> validator.validate(Collections.emptyMap()));
    }

    private ObjectNode createNode(JsonNode x, JsonNode y) {
        ObjectMapper mapper = ObjectMapperFactory.get();
        return mapper.createObjectNode().set("numbers", mapper.createArrayNode().add(mapper.createObjectNode().<ObjectNode> set("x", x).set("y", y)));
    }

}
