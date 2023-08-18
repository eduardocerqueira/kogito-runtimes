package $Package$;

@org.springframework.stereotype.Component
public class ConfigBean extends org.kie.kogito.config.StaticConfigBean {

    @org.springframework.beans.factory.annotation.Value("${kogito.service.url:#{null}}")
    java.util.Optional<java.lang.String> kogitoService;

    @org.springframework.beans.factory.annotation.Value("${kogito.messaging.as-cloudevents:#{true}}")
    boolean useCloudEvents;

    @org.springframework.beans.factory.annotation.Value("${kogito.jackson.fail-on-empty-bean:#{false}}")
    boolean failOnEmptyBean;

    @javax.annotation.PostConstruct
    protected void init() {
        setServiceUrl(kogitoService.orElse(""));
        setCloudEvents(useCloudEvents);
        setFailOnEmptyBean(failOnEmptyBean);
        setGav($gav$);
    }
}
