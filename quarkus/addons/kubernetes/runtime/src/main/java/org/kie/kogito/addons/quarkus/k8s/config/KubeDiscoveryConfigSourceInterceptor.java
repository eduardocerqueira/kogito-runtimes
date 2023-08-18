package org.kie.kogito.addons.quarkus.k8s.config;

import java.text.MessageFormat;
import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

import org.kie.kogito.addons.k8s.resource.catalog.DefaultKubernetesServiceCatalogFactory;
import org.kie.kogito.addons.k8s.resource.catalog.KubernetesServiceCatalog;
import org.kie.kogito.addons.k8s.resource.catalog.KubernetesServiceCatalogProvider;

import io.smallrye.config.ConfigSourceInterceptor;
import io.smallrye.config.ConfigSourceInterceptorContext;
import io.smallrye.config.ConfigValue;

public final class KubeDiscoveryConfigSourceInterceptor implements ConfigSourceInterceptor {

    static final String MULTIPLE_PROVIDERS_FOUND_MSG = "Multiple providers found for {0}: {1}";

    private final transient ConfigValueExpander configValueExpander;

    public KubeDiscoveryConfigSourceInterceptor() {
        KubernetesServiceCatalog kubernetesServiceCatalog = createKubernetesServiceCatalog(getKubernetesServiceCatalogProviders());
        var kubeDiscoveryConfigCacheUpdater = new KubeDiscoveryConfigCacheUpdater(kubernetesServiceCatalog);
        var kubeDiscoveryConfigCache = new KubeDiscoveryConfigCache(kubeDiscoveryConfigCacheUpdater);
        this.configValueExpander = new ConfigValueExpander(kubeDiscoveryConfigCache);
    }

    private static List<KubernetesServiceCatalogProvider> getKubernetesServiceCatalogProviders() {
        return ServiceLoader.load(KubernetesServiceCatalogProvider.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }

    static KubernetesServiceCatalog createKubernetesServiceCatalog(List<KubernetesServiceCatalogProvider> providers) {
        if (providers.size() == 1) {
            return providers.get(0).create();
        } else if (providers.isEmpty()) {
            return DefaultKubernetesServiceCatalogFactory.createKubernetesServiceCatalog();
        } else {
            String providersNames = providers.stream()
                    .map(provider -> provider.getClass().getName())
                    .collect(Collectors.joining(", ", "[", "]"));

            throw new IllegalStateException(MessageFormat.format(MULTIPLE_PROVIDERS_FOUND_MSG,
                    KubernetesServiceCatalogProvider.class, providersNames));
        }
    }

    @Override
    public ConfigValue getValue(ConfigSourceInterceptorContext context, String s) {
        return configValueExpander.expand(context.proceed(s));
    }
}
