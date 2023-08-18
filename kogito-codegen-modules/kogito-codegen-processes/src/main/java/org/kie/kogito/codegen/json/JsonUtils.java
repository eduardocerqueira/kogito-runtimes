package org.kie.kogito.codegen.json;

import org.kie.kogito.jackson.utils.MergeUtils;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonUtils {

    /* see https://stackoverflow.com/questions/9895041/merging-two-json-documents-using-jackson for alternative approaches to merge */
    private JsonUtils() {
    }

    /**
     * Merge two JSON documents.
     *
     * @param src JsonNode to be merged
     * @param target JsonNode to merge to
     */
    public static void merge(JsonNode src, JsonNode target) {
        MergeUtils.merge(src, target, true);
    }

}
