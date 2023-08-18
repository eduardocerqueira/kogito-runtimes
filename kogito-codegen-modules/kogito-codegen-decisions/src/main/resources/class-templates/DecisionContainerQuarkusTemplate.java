package $Package$;

@javax.enterprise.context.ApplicationScoped()
@io.quarkus.runtime.Startup() //<-- Required to force full reload during hot reload
public class DecisionModels extends org.kie.kogito.dmn.AbstractDecisionModels {

    static {
        init(
                /* arguments provided during codegen */);
    }

    @javax.inject.Inject
    protected org.kie.kogito.Application application;

    public DecisionModels() {
        super();
    }

    @javax.annotation.PostConstruct
    protected void init() {
        initApplication(application);
    }
}
