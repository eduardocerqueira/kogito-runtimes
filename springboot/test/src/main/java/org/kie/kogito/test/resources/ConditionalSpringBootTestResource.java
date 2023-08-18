package org.kie.kogito.test.resources;

import java.util.Map;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.test.context.support.TestPropertySourceUtils;

/**
 *
 * Spring Boot test resource.
 *
 */
public abstract class ConditionalSpringBootTestResource<T extends TestResource> implements ApplicationContextInitializer<ConfigurableApplicationContext>, ApplicationListener<ContextClosedEvent> {

    private final T testResource;
    private final ConditionHolder condition;
    private boolean conditionalEnabled = false;

    public ConditionalSpringBootTestResource(T testResource) {
        this(testResource, new ConditionHolder(testResource.getResourceName()));
    }

    public ConditionalSpringBootTestResource(T testResource, ConditionHolder condition) {
        this.testResource = testResource;
        this.condition = condition;
    }

    public T getTestResource() {
        return testResource;
    }

    public boolean isConditionalEnabled() {
        return conditionalEnabled;
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        if (condition.isEnabled()) {
            testResource.start();
            updateBeanFactory(applicationContext.getBeanFactory());
            String[] props = getProperties().entrySet().stream().map(e -> e.getKey() + "=" + e.getValue()).toArray(String[]::new);
            updateContextProperty(applicationContext, props);
        }
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        if (condition.isEnabled()) {
            testResource.stop();
        }
    }

    protected abstract Map<String, String> getProperties();

    protected void enableConditional() {
        condition.enableConditional();
        conditionalEnabled = true;
    }

    protected void updateBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        beanFactory.registerSingleton(testResource.getResourceName() + "ShutDownHook", this);
    }

    protected void updateContextProperty(ConfigurableApplicationContext applicationContext, String[] props) {
        TestPropertySourceUtils.addInlinedPropertiesToEnvironment(applicationContext, props);
    }
}
