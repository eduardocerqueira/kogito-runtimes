package com.myspace.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.kie.kogito.Application;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessService;
import org.kie.kogito.process.impl.ProcessServiceImpl;
import org.kie.kogito.event.impl.AbstractMessageConsumer;

public class $Type$MessageConsumer extends AbstractMessageConsumer<$Type$, $DataType$> {

    Process<$Type$> process;

    Application application;

    boolean useCloudEvents = true;

    ExecutorService executor = Executors.newSingleThreadExecutor();
    
    ProcessService service;

    public void configure() {
        service = new ProcessServiceImpl(application);
    }
}
