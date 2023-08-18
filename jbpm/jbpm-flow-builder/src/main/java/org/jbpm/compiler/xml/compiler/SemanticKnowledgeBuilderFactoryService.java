package org.jbpm.compiler.xml.compiler;

import java.util.Properties;

import org.drools.compiler.builder.impl.CompositeBuilderConfiguration;
import org.drools.compiler.builder.impl.KnowledgeBuilderConfigurationFactories;
import org.drools.compiler.builder.impl.KnowledgeBuilderFactoryServiceImpl;
import org.kie.api.conf.OptionsConfiguration;
import org.kie.internal.builder.KnowledgeBuilderConfiguration;
import org.kie.internal.builder.conf.KnowledgeBuilderOption;
import org.kie.internal.builder.conf.MultiValueKieBuilderOption;
import org.kie.internal.builder.conf.SingleValueKieBuilderOption;
import org.kie.internal.conf.CompositeConfiguration;
import org.kie.internal.conf.ConfigurationFactory;
import org.kie.internal.utils.ChainedProperties;

public class SemanticKnowledgeBuilderFactoryService extends KnowledgeBuilderFactoryServiceImpl {

    @Override
    public KnowledgeBuilderConfiguration newKnowledgeBuilderConfiguration() {
        ClassLoader projClassLoader = getClassLoader(null);

        ChainedProperties chained = ChainedProperties.getChainedProperties(projClassLoader);

        return new CompositeBuilderConfiguration(chained, projClassLoader,
                SemanticConfigurationFactory.INSTANCE,
                KnowledgeBuilderConfigurationFactories.ruleConf,
                KnowledgeBuilderConfigurationFactories.flowConf);
    }

    @Override
    public KnowledgeBuilderConfiguration newKnowledgeBuilderConfiguration(ClassLoader classLoader) {
        return newKnowledgeBuilderConfiguration(null, classLoader);
    }

    @Override
    public KnowledgeBuilderConfiguration newKnowledgeBuilderConfiguration(Properties properties, ClassLoader classLoader) {
        ClassLoader projClassLoader = getClassLoader(classLoader);

        ChainedProperties chained = ChainedProperties.getChainedProperties(projClassLoader);

        if (properties != null) {
            chained.addProperties(properties);
        }

        return new CompositeBuilderConfiguration(chained, projClassLoader,
                SemanticConfigurationFactory.INSTANCE,
                KnowledgeBuilderConfigurationFactories.ruleConf,
                KnowledgeBuilderConfigurationFactories.flowConf);
    }

    @Override
    public int servicePriority() {
        return 1;
    }

    private static class SemanticConfigurationFactory implements ConfigurationFactory<KnowledgeBuilderOption, SingleValueKieBuilderOption, MultiValueKieBuilderOption> {

        private static final SemanticConfigurationFactory INSTANCE = new SemanticConfigurationFactory();

        @Override
        public String type() {
            return "Base";
        }

        @Override
        public OptionsConfiguration<KnowledgeBuilderOption, SingleValueKieBuilderOption, MultiValueKieBuilderOption>
                create(CompositeConfiguration<KnowledgeBuilderOption, SingleValueKieBuilderOption, MultiValueKieBuilderOption> compConfig,
                        ClassLoader classLoader, ChainedProperties chainedProperties) {
            return new SemanticKnowledgeBuilderConfigurationImpl(compConfig);
        }
    };
}
