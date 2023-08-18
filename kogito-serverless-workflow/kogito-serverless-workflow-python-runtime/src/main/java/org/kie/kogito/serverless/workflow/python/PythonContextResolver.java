package org.kie.kogito.serverless.workflow.python;

import java.util.Map;
import java.util.function.Function;

import org.kie.kogito.internal.process.runtime.KogitoProcessContext;
import org.kie.kogito.jackson.utils.FunctionJsonNode;
import org.kie.kogito.serverless.workflow.SWFConstants;
import org.kie.kogito.serverless.workflow.utils.KogitoProcessContextResolverExtension;

public class PythonContextResolver implements KogitoProcessContextResolverExtension {

    @Override
    public Map<String, Function<KogitoProcessContext, Object>> getKogitoProcessContextResolver() {
        return Map.of(SWFConstants.PYTHON, k -> new FunctionJsonNode(PythonWorkItemHandlerUtils::getValue));
    }

}
