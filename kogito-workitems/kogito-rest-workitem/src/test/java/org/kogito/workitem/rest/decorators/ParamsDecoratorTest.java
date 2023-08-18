package org.kogito.workitem.rest.decorators;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kie.kogito.internal.process.runtime.KogitoWorkItem;

import io.vertx.mutiny.ext.web.client.HttpRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ParamsDecoratorTest {

    @Test
    void testPrefixParamsDecorator() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("HEADER_pepe", "pepa");
        parameters.put("QUERY_javierito", "real betis balompie");
        testParamDecorator(new PrefixParamsDecorator(), parameters);
    }

    @Test
    void testCollectionParamsDecorator() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("pepe", "pepa");
        parameters.put("javierito", "real betis balompie");
        testParamDecorator(new CollectionParamsDecorator(Collections.singleton("pepe"), Collections.singleton("javierito")), parameters);
    }

    private void testParamDecorator(ParamsDecorator decorator, Map<String, Object> parameters) {
        HttpRequest<?> request = mock(HttpRequest.class);
        decorator.decorate(mock(KogitoWorkItem.class), parameters, request);
        verify(request).addQueryParam("javierito", "real betis balompie");
        verify(request).putHeader("pepe", "pepa");
    }

}
