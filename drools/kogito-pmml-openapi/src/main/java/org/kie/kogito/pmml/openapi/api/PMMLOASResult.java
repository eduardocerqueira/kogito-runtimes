package org.kie.kogito.pmml.openapi.api;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface PMMLOASResult {

    String DEFINITIONS = "definitions";
    String INPUT_SET = "InputSet";
    String RESULT_SET = "ResultSet";
    String OUTPUT_SET = "OutputSet";
    String REQUIRED = "required";
    String PROPERTIES = "properties";
    String TYPE = "type";
    String FORMAT = "format";
    String DEFAULT = "default";
    String ENUM = "enum";
    String INTERVALS = "intervals";
    String OBJECT = "object";
    String STRING = "string";
    String BOOLEAN = "boolean";
    String INTEGER = "integer";
    String NUMBER = "number";
    String DOUBLE = "double";
    String FLOAT = "float";
    String CORRELATION_ID = "correlationId";
    String SEGMENTATION_ID = "segmentationId";
    String SEGMENT_ID = "segmentId";
    String SEGMENT_INDEX = "segmentIndex";
    String RESULT_CODE = "resultCode";
    String RESULT_OBJECT_NAME = "resultObjectName";
    String RESULT_VARIABLES = "resultVariables";
    String MINIMUM = "minimum";
    String MAXIMUM = "maximum";

    ObjectNode jsonSchemaNode();
}
