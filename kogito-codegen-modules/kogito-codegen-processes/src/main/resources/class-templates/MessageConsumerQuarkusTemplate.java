package $Package$;


import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import javax.inject.Inject;
import javax.inject.Named;

import org.kie.kogito.process.Process;
import org.kie.kogito.addon.quarkus.messaging.common.QuarkusMessageConsumer;
import org.kie.kogito.event.EventReceiver;


@io.quarkus.runtime.Startup
public class $Type$MessageConsumer extends QuarkusMessageConsumer<$Type$, $DataType$> {

    @Inject
    @Named("$ProcessName$")
    Process<$Type$> process;
    
    @Inject
    EventReceiver eventReceiver;
    
    private Set<String> correlation;

    @javax.annotation.PostConstruct
    void init() {
        init(process,"$Trigger$",$DataType$.class, eventReceiver, correlation);
    }

    private $Type$ eventToModel($DataType$ event) {
        $Type$ model = new $Type$();
        if(event != null) {
            model.$SetModelMethodName$(event);
        }
        return model;
    }

    @Override()
    protected Optional<Function<$DataType$, $Type$>> getModelConverter() {
        return Optional.of(this::eventToModel);
    }
}
