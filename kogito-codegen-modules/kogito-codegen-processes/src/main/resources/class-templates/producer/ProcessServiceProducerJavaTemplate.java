package $Package$;

import org.kie.kogito.Application;
import org.kie.kogito.process.ProcessService;
import org.kie.kogito.process.impl.ProcessServiceImpl;

public class ProcessServiceProducer {

    public ProcessService processService(Application application){
        return new ProcessServiceImpl(application);
    }
}
