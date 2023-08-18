package $Package$;


import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;

import org.kie.kogito.addon.quarkus.messaging.common.AbstractQuarkusCloudEventEmitter;

import io.quarkus.runtime.Startup;

@Startup
@ApplicationScoped
public class $Trigger$EventEmitter extends AbstractQuarkusCloudEventEmitter<$Type$> {
    @Inject
    @Channel("$Trigger$")
    Emitter<$Type$> emitter;
    
    @Override
    protected void emit (Message<$Type$> message) {
        emitter.send(message);
    }
    
    @PostConstruct
    void init () {
    }
}
