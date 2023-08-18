package org.kogito.workitem.rest.auth;

import java.util.Map;

import org.kie.kogito.internal.process.runtime.KogitoWorkItem;

import io.vertx.mutiny.ext.web.client.HttpRequest;

import static org.kie.kogito.internal.utils.ConversionUtils.isEmpty;
import static org.kogito.workitem.rest.RestWorkItemHandler.PASSWORD;
import static org.kogito.workitem.rest.RestWorkItemHandler.USER;
import static org.kogito.workitem.rest.RestWorkItemHandlerUtils.getParam;

public class BasicAuthDecorator implements AuthDecorator {

    @Override
    public void decorate(KogitoWorkItem item, Map<String, Object> parameters, HttpRequest<?> request) {
        String user = getParam(parameters, USER, String.class, null);
        String password = getParam(parameters, PASSWORD, String.class, null);

        if (!isEmpty(user) && !isEmpty(password)) {
            request.basicAuthentication(user, password);
        }
    }
}
