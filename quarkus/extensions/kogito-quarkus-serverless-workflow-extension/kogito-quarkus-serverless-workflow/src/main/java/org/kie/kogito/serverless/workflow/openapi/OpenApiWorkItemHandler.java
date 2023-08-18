package org.kie.kogito.serverless.workflow.openapi;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

import org.kie.kogito.event.cloudevents.extension.ProcessMeta;
import org.kie.kogito.internal.process.runtime.KogitoWorkItem;
import org.kie.kogito.process.workitem.WorkItemExecutionException;
import org.kie.kogito.serverless.workflow.WorkflowWorkItemHandler;

import io.quarkus.restclient.runtime.RestClientBuilderFactory;

public abstract class OpenApiWorkItemHandler<T> extends WorkflowWorkItemHandler {

    @Override
    protected Object internalExecute(KogitoWorkItem workItem, Map<String, Object> parameters) {
        Class<T> clazz = getRestClass();
        T ref = RestClientBuilderFactory.build(clazz).register(new ClientRequestFilter() {
            @Override
            public void filter(ClientRequestContext requestContext) throws IOException {
                ProcessMeta.fromKogitoWorkItem(workItem).asMap().forEach((k, v) -> requestContext.getHeaders().put(k, Collections.singletonList(v)));
            }
        }).build(clazz);
        try {
            return internalExecute(ref, parameters);
        } catch (WebApplicationException ex) {
            throw new WorkItemExecutionException(Integer.toString(ex.getResponse().getStatus()), ex.getMessage());
        }
    }

    protected abstract Object internalExecute(T openAPIRef, Map<String, Object> parameters);

    protected Class<T> getRestClass() {
        // this does not work in quarkus dev mode, overriding through generation
        return (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
