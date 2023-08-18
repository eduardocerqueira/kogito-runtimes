package org.kie.kogito.quarkus.serverless.workflow.openapi;

import java.util.Map;

import org.eclipse.microprofile.openapi.OASFilter;
import org.eclipse.microprofile.openapi.models.OpenAPI;
import org.eclipse.microprofile.openapi.models.media.Schema;
import org.kie.kogito.serverless.workflow.parser.schema.OpenApiModelSchemaGenerator;

public final class ServerlessWorkflowOASFilter implements OASFilter {

    private final Map<String, Schema> schemasInfo;

    public ServerlessWorkflowOASFilter(Map<String, Schema> schemasInfo) {
        this.schemasInfo = schemasInfo;
    }

    @Override
    public void filterOpenAPI(OpenAPI openAPI) {
        OpenApiModelSchemaGenerator.mergeSchemas(openAPI, schemasInfo);
    }
}
