package io.quarkus.restclient.runtime;

import org.eclipse.microprofile.context.ManagedExecutor;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.quarkus.arc.Arc;
import io.quarkus.arc.InstanceHandle;

public class RestClientBuilderFactory extends RestClientBase {

    public RestClientBuilderFactory(Class<?> proxyType, String baseUriFromAnnotation, String configKey) {
        super(proxyType, baseUriFromAnnotation, configKey, new Class[0]);
    }

    public static RestClientBuilder build(Class<?> restClass) {
        RegisterRestClient annotation = restClass.getAnnotation(RegisterRestClient.class);
        RestClientBuilderFactory instance = new RestClientBuilderFactory(restClass, annotation.baseUri(), annotation.configKey());
        RestClientBuilder builder = RestClientBuilder.newBuilder();
        instance.configureBaseUrl(builder);
        instance.configureTimeouts(builder);
        instance.configureProviders(builder);
        instance.configureSsl(builder);
        instance.configureProxy(builder);
        instance.configureRedirects(builder);
        instance.configureQueryParamStyle(builder);
        instance.configureCustomProperties(builder);
        // If we have context propagation, then propagate context to the async client threads
        InstanceHandle<ManagedExecutor> managedExecutor = Arc.container().instance(ManagedExecutor.class);
        if (managedExecutor.isAvailable()) {
            builder.executorService(managedExecutor.get());
        }
        return builder;
    }

}
