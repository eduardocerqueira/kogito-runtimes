package org.kie.kogito.mongodb.utils;

public class DocumentConstants {

    public static final String VARIABLE = "variable";
    public static final String VALUE = "value";
    public static final String DOCUMENT_ID = "_id";
    public static final String PROCESS_INSTANCE_ID = "id";
    public static final String PROCESS_INSTANCE_ID_INDEX = "index_process_instance_id";
    public static final String STRATEGIES = "strategies";
    public static final String NAME = "name";
    public static final String PROCESS_INSTANCE = "processInstance";
    public static final String DOCUMENT_MARSHALLING_ERROR_MSG = "Error while marshalling process instance with id as document : ";
    public static final String DOCUMENT_UNMARSHALLING_ERROR_MSG = "Error while unmarshalling document for process instance with id : ";

    private DocumentConstants() {
    }
}
