package org.kie.kogito.monitoring.elastic.springboot;

import javax.annotation.PostConstruct;

import org.kie.kogito.monitoring.elastic.common.ElasticConfigFactory;
import org.kie.kogito.monitoring.elastic.common.ElasticRegistry;
import org.kie.kogito.monitoring.elastic.common.KogitoElasticConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class SpringbootElasticRegistryProvider extends ElasticRegistry {

    @Value(value = "${kogito.addon.monitoring.elastic.host:#{null}}")
    public String elasticHost;
    @Value(value = "${kogito.addon.monitoring.elastic.index:#{null}}")
    public String index;
    @Value(value = "${kogito.addon.monitoring.elastic.step:#{null}}")
    public String step;
    @Value(value = "${kogito.addon.monitoring.elastic.indexDateFormat:#{null}}")
    public String indexDateFormat;
    @Value(value = "${kogito.addon.monitoring.elastic.timestampFieldName:#{null}}")
    public String timestampFieldName;
    @Value(value = "${kogito.addon.monitoring.elastic.autoCreateIndex:#{null}}")
    public String autoCreateIndex;
    @Value(value = "${kogito.addon.monitoring.elastic.userName:#{null}}")
    public String userName;
    @Value(value = "${kogito.addon.monitoring.elastic.password:#{null}}")
    public String password;
    @Value(value = "${kogito.addon.monitoring.elastic.pipeline:#{null}}")
    public String pipeline;
    @Value(value = "${kogito.addon.monitoring.elastic.indexDateSeparator:#{null}}")
    public String indexDateSeparator;
    @Value(value = "${kogito.addon.monitoring.elastic.documentType:#{null}}")
    public String documentType;

    @Autowired
    ThreadPoolTaskExecutor executor;

    @PostConstruct
    protected void onStart() {
        ElasticConfigFactory elasticConfigFactory = new ElasticConfigFactory();
        elasticConfigFactory.withProperty(KogitoElasticConfig.HOST_KEY, elasticHost);
        elasticConfigFactory.withProperty(KogitoElasticConfig.INDEX_KEY, index);
        elasticConfigFactory.withProperty(KogitoElasticConfig.STEP_KEY, step);
        elasticConfigFactory.withProperty(KogitoElasticConfig.INDEX_DATE_FORMAT_KEY, indexDateFormat);
        elasticConfigFactory.withProperty(KogitoElasticConfig.TIMESTAMP_FIELD_NAME_KEY, timestampFieldName);
        elasticConfigFactory.withProperty(KogitoElasticConfig.AUTO_CREATE_INDEX_KEY, autoCreateIndex);
        elasticConfigFactory.withProperty(KogitoElasticConfig.USERNAME_KEY, userName);
        elasticConfigFactory.withProperty(KogitoElasticConfig.PASSWORD_KEY, password);
        elasticConfigFactory.withProperty(KogitoElasticConfig.PIPELINE_KEY, pipeline);
        elasticConfigFactory.withProperty(KogitoElasticConfig.INDEX_DATE_SEPARATOR_KEY, indexDateSeparator);
        elasticConfigFactory.withProperty(KogitoElasticConfig.DOCUMENT_TYPE_KEY, documentType);
        super.start(elasticConfigFactory.getElasticConfig(), executor);
    }
}