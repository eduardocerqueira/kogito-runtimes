package $Package$;


import java.util.concurrent.CompletionStage;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.kie.kogito.addon.quarkus.messaging.common.AbstractQuarkusCloudEventReceiver ;


import io.quarkus.runtime.Startup;

@Startup
@ApplicationScoped
public class $Trigger$EventReceiver extends AbstractQuarkusCloudEventReceiver<$Type$> {
    @Incoming("$Trigger$")
    public CompletionStage<?> onEvent(Message<$Type$> message) {
        return produce(message);
    }
      
    @PostConstruct
    void init() {
    }
}
