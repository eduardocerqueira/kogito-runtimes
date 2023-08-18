package org.kie.kogito.serverless.workflow.utils;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.serverlessworkflow.api.Workflow;
import io.serverlessworkflow.api.functions.FunctionDefinition;
import io.swagger.parser.OpenAPIParser;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.core.models.SwaggerParseResult;

import static org.kie.kogito.serverless.workflow.io.URIContentLoaderFactory.buildLoader;
import static org.kie.kogito.serverless.workflow.io.URIContentLoaderFactory.readAllBytes;

public class OpenAPIFactory {

    private static final Logger logger = LoggerFactory.getLogger(OpenAPIFactory.class);

    private OpenAPIFactory() {
    }

    public static OpenAPI getOpenAPI(URI uri, Workflow workflow, FunctionDefinition function, ClassLoader cl) {
        SwaggerParseResult result =
                new OpenAPIParser().readContents(new String(readAllBytes(buildLoader(uri, cl, workflow, function.getAuthRef()))), null, null);
        OpenAPI openAPI = result.getOpenAPI();
        if (openAPI == null) {
            throw new IllegalArgumentException("Problem parsing uri " + uri + " Messages" + result.getMessages());
        }
        logger.debug("OpenAPI parser messages {}", result.getMessages());
        return openAPI;
    }
}
