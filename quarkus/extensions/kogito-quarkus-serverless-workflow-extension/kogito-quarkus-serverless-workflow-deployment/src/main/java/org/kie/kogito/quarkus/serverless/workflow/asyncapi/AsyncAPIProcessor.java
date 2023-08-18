package org.kie.kogito.quarkus.serverless.workflow.asyncapi;

import java.util.ServiceLoader;

import org.kie.kogito.quarkus.common.deployment.KogitoAddonsPreGeneratedSourcesBuildItem;
import org.kie.kogito.quarkus.common.deployment.KogitoBuildContextBuildItem;
import org.kie.kogito.quarkus.common.deployment.LiveReloadExecutionBuildItem;
import org.kie.kogito.serverless.workflow.parser.ParserContext;

import io.quarkiverse.asyncapi.config.AsyncAPISupplier;
import io.quarkiverse.asyncapi.config.MapAsyncAPIRegistry;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;

public class AsyncAPIProcessor {

    @BuildStep
    void asyncAPIContext(LiveReloadExecutionBuildItem reload, KogitoBuildContextBuildItem context, BuildProducer<KogitoAddonsPreGeneratedSourcesBuildItem> sources) {
        context.getKogitoBuildContext().addContextAttribute(ParserContext.ASYNC_CONVERTER_KEY, new AsyncAPIInfoConverter(
                new MapAsyncAPIRegistry(ServiceLoader.load(AsyncAPISupplier.class, reload.getClassLoader().orElse(Thread.currentThread().getContextClassLoader())))));
    }
}
