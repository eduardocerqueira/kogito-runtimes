package $Package$;

import org.kie.kogito.Application;
import org.kie.kogito.process.ProcessService;
import org.kie.kogito.process.impl.ProcessServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessServiceProducer {

    @Bean
    public ProcessService processService(Application application){
        return new ProcessServiceImpl(application);
    }
}
