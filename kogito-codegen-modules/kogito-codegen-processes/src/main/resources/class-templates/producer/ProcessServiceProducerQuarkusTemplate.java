package $Package$;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.kie.kogito.Application;
import org.kie.kogito.process.ProcessService;
import org.kie.kogito.process.impl.ProcessServiceImpl;



@ApplicationScoped
public class ProcessServiceProducer {

    @Produces
    public ProcessService processService(Application application){
        return new ProcessServiceImpl(application);
    }
}
