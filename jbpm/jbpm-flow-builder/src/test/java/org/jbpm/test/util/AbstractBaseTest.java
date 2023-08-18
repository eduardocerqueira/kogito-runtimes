package org.jbpm.test.util;

import org.drools.compiler.builder.impl.KnowledgeBuilderImpl;
import org.drools.core.impl.RuleBaseFactory;
import org.jbpm.process.instance.InternalProcessRuntime;
import org.jbpm.process.instance.impl.util.LoggingPrintStream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.runtime.conf.ForceEagerActivationOption;
import org.kie.kogito.internal.process.runtime.KogitoProcessRuntime;

public abstract class AbstractBaseTest {

    protected KnowledgeBuilderImpl builder;

    @BeforeEach
    public void before() {
        builder = (KnowledgeBuilderImpl) KnowledgeBuilderFactory.newKnowledgeBuilder(KnowledgeBuilderFactory.newKnowledgeBuilderConfiguration());
    }

    public KogitoProcessRuntime createKogitoProcessRuntime() {
        return InternalProcessRuntime.asKogitoProcessRuntime(createKieSession());
    }

    @Deprecated
    public KieSession createKieSession() {
        KieSessionConfiguration conf = RuleBaseFactory.newKnowledgeSessionConfiguration();
        conf.setOption(ForceEagerActivationOption.YES);
        return builder.newKieBase().newKieSession(conf, null);
    }

    @BeforeAll
    public static void configure() {
        LoggingPrintStream.interceptSysOutSysErr();
    }

    @AfterAll
    public static void reset() {
        LoggingPrintStream.resetInterceptSysOutSysErr();
    }
}
