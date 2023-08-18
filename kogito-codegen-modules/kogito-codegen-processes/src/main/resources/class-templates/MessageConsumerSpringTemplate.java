package $Package$;

import java.util.Optional;
import java.util.function.Function;

import org.kie.kogito.process.Process;
import org.kie.kogito.addon.cloudevents.spring.SpringMessageConsumer;
import org.kie.kogito.event.EventReceiver;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


@org.springframework.stereotype.Component()
public class $Type$MessageConsumer extends SpringMessageConsumer<$Type$, $DataType$> {

    @Autowired
    @Qualifier("$ProcessName$") 
    Process<$Type$> process;
    
    
    @Autowired
    EventReceiver eventReceiver;

    
    @PostConstruct
    void init() { 
    	init (process, "$Trigger$", $DataType$.class, eventReceiver);
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
